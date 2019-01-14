package lt.freeland.uaa.controller;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lt.freeland.common.exceptions.ReCaptchaInvalidException;
import lt.freeland.common.exceptions.ReCaptchaUnavailableException;
import lt.freeland.uaa.beans.UserRegistrationForm;
import lt.freeland.uaa.service.CaptchaService;
import lt.freeland.uaa.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
    
    private final UserRegistrationService registrationService;
    private final CaptchaService captchaService;
    private final MessageSource messageSource;

    @Autowired
    public RegistrationController(UserRegistrationService registrationService, CaptchaService captchaService, MessageSource messageSource) {
        this.registrationService = registrationService;
        this.captchaService = captchaService;
        this.messageSource = messageSource;
    }
    
    @PostMapping("/register")
    public ModelAndView registerUser(@Valid @ModelAttribute("user") UserRegistrationForm userRegistration, HttpServletRequest request, ModelMap mm){
        String response = request.getParameter("g-recaptcha-response");
        
        try {
            captchaService.processResponse(response);
        } catch (ReCaptchaInvalidException | ReCaptchaUnavailableException ex) {
            return new ModelAndView("login", "error", ex.getMessage());
        }
        
        if(registrationService.checkIfUsernameExists(userRegistration.getUsername())) {
            mm.addAttribute("error", messageSource.getMessage("username.exists", new Object[]{userRegistration.getUsername()}, null));
            return new ModelAndView("login", mm);
        }
        
        if(registrationService.checkIfEmailExists(userRegistration.getEmail())){
            mm.addAttribute("error", messageSource.getMessage("email.exists", new Object[]{userRegistration.getEmail()}, null));
            return new ModelAndView("login", mm);
        }
        
        return new ModelAndView("login", mm);
    }
}
