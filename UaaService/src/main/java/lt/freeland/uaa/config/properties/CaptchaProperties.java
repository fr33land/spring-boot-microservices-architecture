package lt.freeland.uaa.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author r.sabaliauskas
 */
@Component
@ConfigurationProperties(prefix = "google.recaptcha.key")
public class CaptchaProperties {
 
    private String site;
    private String secret;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }    
}
