package lt.freeland.uaa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import lt.freeland.common.domain.User;
import lt.freeland.common.utils.Utils;
import lt.freeland.uaa.beans.UserRegistration;
import lt.freeland.uaa.exceptions.ReCaptchaInvalidException;
import lt.freeland.uaa.exceptions.ReCaptchaUnavailableException;
import lt.freeland.uaa.exceptions.TokenExpiredException;
import lt.freeland.uaa.exceptions.TokenNotFoundException;
import lt.freeland.uaa.exceptions.UserActivatedException;
import lt.freeland.uaa.exceptions.UserNotFoundException;
import lt.freeland.uaa.service.CaptchaService;
import lt.freeland.uaa.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author freeland
 */
@Slf4j
@Controller
public class RegistrationController {

    private final RegistrationService registrationService;
    private final PasswordEncoder passwordEncoder;
    private final CaptchaService captchaService;
    private final MessageSource messageSource;

    @Autowired
    public RegistrationController(RegistrationService registrationService, PasswordEncoder passwordEncoder, CaptchaService captchaService, MessageSource messageSource) {
        this.registrationService = registrationService;
        this.passwordEncoder = passwordEncoder;
        this.captchaService = captchaService;
        this.messageSource = messageSource;
    }

    @PostMapping("/user/register")
    public ModelAndView registerUser(@Valid @ModelAttribute("user") UserRegistration ur, BindingResult result, HttpServletRequest request, ModelMap mm, RedirectAttributes ra) {
        String response = request.getParameter("g-recaptcha-response");
        
        if (result.hasErrors()) {
            mm.addAttribute("regTab", true);
            return new ModelAndView("login", "user", ur);
        }

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
            mm.addAttribute("error", messageSource.getMessage("email.user_exists", new Object[]{ur.getEmail()}, null));
            return new ModelAndView("login", mm);
        }

        User newUser = registrationService.registerUser(ur, Utils.getAppUrl(request));

        if (newUser.getUserId() != null) {
            ra.addFlashAttribute("message", messageSource.getMessage("user.registration_succeed", new Object[]{newUser.getEmail()}, null));
            return new ModelAndView("redirect:/login");
        } else {
            mm.addAttribute("regTab", true);
            mm.addAttribute("error", messageSource.getMessage("user.registration_failed", null, null));
            mm.addAttribute("user", newUser);
            return new ModelAndView("login", mm);
        }
    }

    @GetMapping("/user/activation/confirm/{token}")
    public ModelAndView confirmAccount(HttpServletRequest request, @PathVariable(value = "token") String token, ModelMap mm) {
        mm.addAttribute("user", new UserRegistration());

        try {
            registrationService.checkConfirmationTokenIsvalid(token);
            registrationService.confirmAccount(token);
        } catch (TokenExpiredException | TokenNotFoundException | UserNotFoundException ex) {
            mm.addAttribute("error", ex.getMessage());

            if (ex instanceof TokenExpiredException) {
                mm.addAttribute("resendActivation", true);
            }

            return new ModelAndView("login", mm);
        }

        return new ModelAndView("login", "message", messageSource.getMessage("user.confirmation_succeed", null, null));
    }

    @GetMapping("/user/activation")
    public ModelAndView sendActivationLink(HttpServletRequest request, ModelMap mm) {
        mm.addAttribute("user", new UserRegistration());
        return new ModelAndView("/user/registration/activation");
    }

    @PostMapping("/user/activation")
    public ModelAndView proceedActivationLink(@ModelAttribute("user") UserRegistration user, HttpServletRequest request, ModelMap mm, RedirectAttributes ra) {

        try {
            registrationService.generateAndSend(user, Utils.getAppUrl(request));
            ra.addFlashAttribute("message", messageSource.getMessage("user.activation_link_succeed", new Object[]{user.getEmail()}, null));
        } catch (UserActivatedException | UserNotFoundException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
        }

        return new ModelAndView("redirect:/login");
    }
}
