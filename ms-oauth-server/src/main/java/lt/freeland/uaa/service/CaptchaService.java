package lt.freeland.uaa.service;

import java.net.URI;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import lt.freeland.uaa.exceptions.ReCaptchaInvalidException;
import lt.freeland.uaa.exceptions.ReCaptchaUnavailableException;
import lt.freeland.uaa.beans.GoogleResponse;
import lt.freeland.uaa.config.properties.CaptchaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

/**
 *
 * @author r.sabaliauskas
 */
@Slf4j
@Service
public class CaptchaService {

    private final HttpServletRequest request;
    private final CaptchaProperties captchaProperties;
    private final RestOperations restTemplate;

    private static final Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

    @Autowired
    public CaptchaService(HttpServletRequest request, CaptchaProperties captchaProperties, @Qualifier("captchaRestTemplate") RestOperations restTemplate) {
        this.request = request;
        this.captchaProperties = captchaProperties;
        this.restTemplate = restTemplate;
    }

    public void processResponse(final String response) throws ReCaptchaInvalidException, ReCaptchaUnavailableException {
        log.debug("Attempting to validate response {}", response);

        if (!responseSanityCheck(response)) {
            throw new ReCaptchaInvalidException("reCaptcha validation failed");
        }

        final URI verifyUri = URI.create(
                String.format(
                        "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s",
                        captchaProperties.getSecret(),
                        response,
                        getClientIP()
                )
        );
        
        try {
            final GoogleResponse googleResponse = restTemplate.getForObject(verifyUri, GoogleResponse.class);
            log.debug("Google's response: {} ", googleResponse.toString());

            if (!googleResponse.isSuccess()) {
                throw new ReCaptchaInvalidException("reCaptcha validation failed");
            }
        } catch (RestClientException rce) {
            log.error("Captcha rest error: {} ", rce.getMessage());
            throw new ReCaptchaUnavailableException("reCaptcha unavailable at this time.  Please try again later.", rce);
        }
    }

    private boolean responseSanityCheck(final String response) {
        return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
    }

    private String getClientIP() {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
