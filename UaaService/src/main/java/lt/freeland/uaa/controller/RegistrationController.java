package lt.freeland.uaa.controller;

import java.time.LocalDateTime;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lt.freeland.common.domain.Role;
import lt.freeland.common.domain.User;
import lt.freeland.common.exceptions.ReCaptchaInvalidException;
import lt.freeland.common.exceptions.ReCaptchaUnavailableException;
import lt.freeland.uaa.beans.UserRegistrationForm;
import lt.freeland.uaa.service.CaptchaService;
import lt.freeland.uaa.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author freeland
 */
@Controller
public class RegistrationController {

    private final UserRegistrationService registrationService;
    private final PasswordEncoder passwordEncoder;
    private final CaptchaService captchaService;
    private final MessageSource messageSource;

    @Autowired
    public RegistrationController(UserRegistrationService registrationService, PasswordEncoder passwordEncoder, CaptchaService captchaService, MessageSource messageSource) {
        this.registrationService = registrationService;
        this.passwordEncoder = passwordEncoder;
        this.captchaService = captchaService;
        this.messageSource = messageSource;
    }

    @PostMapping("/user/register")
    public ModelAndView registerUser(@Valid @ModelAttribute("user") UserRegistrationForm ur, HttpServletRequest request, ModelMap mm, RedirectAttributes ra) {
        String response = request.getParameter("g-recaptcha-response");

        try {
            captchaService.processResponse(response);
        } catch (ReCaptchaInvalidException | ReCaptchaUnavailableException ex) {
            mm.addAttribute("regTab", true);
            mm.addAttribute("error", ex.getMessage());
            return new ModelAndView("login", mm);
        }

        if (registrationService.checkIfUsernameExists(ur.getUsername())) {
            mm.addAttribute("regTab", true);
            mm.addAttribute("error", messageSource.getMessage("username.exists", new Object[]{ur.getUsername()}, null));
            return new ModelAndView("login", mm);
        }

        if (registrationService.checkIfEmailExists(ur.getEmail())) {
            mm.addAttribute("regTab", true);
            mm.addAttribute("error", messageSource.getMessage("email.exists", new Object[]{ur.getEmail()}, null));
            return new ModelAndView("login", mm);
        }

        User user = new User();
        user.setEmail(ur.getEmail());
        user.setUsername(ur.getUsername());
        user.setPassword(passwordEncoder.encode(ur.getPassword()));
        user.setEnabled((short) 0);
        user.setCreatedDate(LocalDateTime.now());

        user = registrationService.registerUser(user);

        if (user.getUserId() != null) {
            registrationService.generateAndSendActivation(user, getAppUrl(request));
            
            ra.addFlashAttribute("message", messageSource.getMessage("user.registration_succeed", new Object[]{user.getEmail()}, null));
            return new ModelAndView("redirect:/login");
        } else {
            mm.addAttribute("regTab", true);
            mm.addAttribute("error", messageSource.getMessage("user.registration_failed", null, null));
            mm.addAttribute("user", user);
            return new ModelAndView("login", mm);
        }
    }

    private String getAppUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + (request.getServerPort() != 80 ? ":" + request.getServerPort() : "");
    }
}
