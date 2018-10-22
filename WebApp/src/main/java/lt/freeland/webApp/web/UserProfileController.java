package lt.freeland.webApp.web;

import lt.freeland.webApp.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author freeland
 */
@Controller
public class UserProfileController {
    
    @Autowired
    UserDataService userDataService;

    @GetMapping(value = "/profile")
    public String userProfile() {
        //userDataService.findUserById(2L);
        return "profile";
    }    
}
