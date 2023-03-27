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
public class DashboardController {
    
    @Autowired
    UserDataService userDataService;

    @GetMapping(value = "/dashboard")
    public String dashboard() {
        return "dashboard/dashboard";
    }    
}
