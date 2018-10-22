package lt.freeland.webApp.beans;

import lt.freeland.webApp.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.client.filter.OAuth2AuthenticationFailureEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author freeland
 */
@Component
public class UserAuthenticationEventHandler {
    
    @Autowired
    UserDataService userDataService;    
    
    @EventListener
    public void handleAuthenticationSuccess(AuthenticationSuccessEvent event) {
        System.out.println("event: " + event);
    }
    
    @EventListener
    public void handleOAuth2AuthenticationFailure(OAuth2AuthenticationFailureEvent event) {
        System.out.println("event: " + event);
    }
    
}
