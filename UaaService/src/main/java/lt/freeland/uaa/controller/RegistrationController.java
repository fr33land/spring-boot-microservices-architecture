package lt.freeland.uaa.controller;

import javax.validation.Valid;
import lt.freeland.common.dto.UserRegistration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author freeland
 */
@Controller
public class RegistrationController {
    
    @PostMapping("/register")
    public ModelAndView registerUser(@ModelAttribute("user") @Valid UserRegistration userRegistration, ModelMap mm){
        return null;
    }
}
