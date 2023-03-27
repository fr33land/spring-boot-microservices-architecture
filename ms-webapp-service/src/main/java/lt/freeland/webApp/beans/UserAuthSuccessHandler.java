package lt.freeland.webApp.beans;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lt.freeland.webApp.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author freeland
 */
@Component
public class UserAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler  {
    
    @Autowired
    UserDataService userDataService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession(false);        
        if (session != null) {
            session.setAttribute("loggedUser", userDataService.findUserByUserName(authentication.getName()));
        }        
        
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
