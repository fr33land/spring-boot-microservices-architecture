package lt.freeland.uaa.controller;

import lt.freeland.uaa.beans.UserRegistrationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author freeland
 */
@Controller
public class LoginController {

    @GetMapping(value = "/login?registered")
    public ModelAndView loginRegistered(WebRequest request, ModelMap mm) {
        UserRegistrationForm user = new UserRegistrationForm();
        mm.addAttribute("user", user);
        return new ModelAndView("login", "user", user);
    }
    
    @GetMapping(value = "/login")
    public ModelAndView loginForm(WebRequest request, ModelMap mm) {
        return new ModelAndView("login", "user", new UserRegistrationForm());
    }
}
