package lt.freeland.webApp.controllers;

import lt.freeland.webApp.services.UserDataService;
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
