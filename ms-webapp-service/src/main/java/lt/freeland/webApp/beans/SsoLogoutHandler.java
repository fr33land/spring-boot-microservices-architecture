package lt.freeland.webApp.beans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author freeland
 */
@Component
public class SsoLogoutHandler implements LogoutHandler {

    @Value("${security.oauth2.resource.tokenInvalidateUri}")
    String logoutUrl;    

    @Autowired
    private OAuth2RestTemplate template;

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        Object details = authentication.getDetails();
        
        if (details.getClass().isAssignableFrom(OAuth2AuthenticationDetails.class)) {
            String accessToken = ((OAuth2AuthenticationDetails) details).getTokenValue();

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("access_token", accessToken);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);
            HttpEntity<String> request = new HttpEntity(params, headers);
            try {
                ResponseEntity<String> response = template.exchange(logoutUrl, HttpMethod.POST, request, String.class);
            } catch (HttpClientErrorException e) {
                //LOGGER.error("HttpClientErrorException invalidating token with SSO authorization server. response.status code: {}, server URL: {}", e.getStatusCode(), logoutUrl);
            }
        }
    }
}
