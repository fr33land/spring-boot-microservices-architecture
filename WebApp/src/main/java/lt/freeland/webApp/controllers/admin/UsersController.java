package lt.freeland.webApp.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author freeland
 */

@Controller
@RequestMapping("/admin/users")
public class UsersController {
    
    @GetMapping(value = "/")
    public String usersList(){
        return "admin/users";
    }
    
}
