package lt.freeland.uaa.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import lt.freeland.common.entities.User;
import lt.freeland.common.entities.PasswordResetToken;
import lt.freeland.common.utils.Utils;
import lt.freeland.uaa.beans.UserRegistration;
import lt.freeland.uaa.service.UserService;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author r.sabaliauskas
 */
@Slf4j
@Controller
@SessionAttributes("tokenId")
public class UserController {

    private final MessageSource messageSource;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(MessageSource messageSource, UserService userService, PasswordEncoder passwordEncoder) {
        this.messageSource = messageSource;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/user/password/reset")
    public ModelAndView userPassword(ModelMap mm) {
        mm.addAttribute("user", new UserRegistration());
        return new ModelAndView("/user/password/reset");
    }

    @PostMapping("/user/password/reset")
    public ModelAndView userPasswordReset(@ModelAttribute("user") UserRegistration user, BindingResult result, ModelMap mm, HttpServletRequest request, RedirectAttributes ra) {

        if (result.hasErrors()) {
            return new ModelAndView("/user/password/reset", "user", user);
        }

        User newUser = userService.getUserByEmail(user.getEmail());

        if (newUser != null) {
            userService.generateAndSendPasswordResetLink(newUser, Utils.getAppUrl(request));
            ra.addFlashAttribute("message", messageSource.getMessage("user.password_reset", new Object[]{user.getEmail()}, null));
            return new ModelAndView("redirect:/login");
        } else {
            ra.addFlashAttribute("error", messageSource.getMessage("user.not_found", new Object[]{user.getEmail()}, null));
            return new ModelAndView("redirect:/user/password/reset");
        }

    }

    @GetMapping("/user/password/reset/{token}")
    public ModelAndView userPasswordChange(@PathVariable("token") String tokenId, ModelMap mm, HttpSession session, RedirectAttributes ra) {
        mm.addAttribute("user", new UserRegistration());
        PasswordResetToken token = userService.getPasswordResetToken(tokenId);

        if (token == null) {
            ra.addFlashAttribute("error", messageSource.getMessage("user.password_link_not_found", null, null));
            return new ModelAndView("redirect:/login");
        } else if (token.getExpireDate().until(LocalDateTime.now(), ChronoUnit.HOURS) > 0) {
            ra.addFlashAttribute("error", messageSource.getMessage("user.password_link_expired", null, null));
            return new ModelAndView("redirect:/login");
        } else {
            session.setAttribute("tokenId", tokenId);
            return new ModelAndView("/user/password/change", mm);
        }
    }

    @PostMapping("/user/password/change")
    public ModelAndView userPasswordChange(@ModelAttribute("user") UserRegistration user, HttpSession session, RedirectAttributes ra) {
        User newUser = new User();
        String tokenId = session.getAttribute("tokenId").toString();
        PasswordResetToken token = userService.getPasswordResetToken(tokenId);
        
        session.removeAttribute("tokenId");
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        
        if (token == null) {
            ra.addFlashAttribute("error", messageSource.getMessage("user.password_link_not_found", null, null));
            return new ModelAndView("redirect:/login");
        } else if (token.getExpireDate().until(LocalDateTime.now(), ChronoUnit.HOURS) > 0) {
            ra.addFlashAttribute("error", messageSource.getMessage("user.password_link_expired", null, null));
            return new ModelAndView("redirect:/login");
        } else {
            userService.updateUserPassword(newUser, tokenId);
            ra.addFlashAttribute("message", messageSource.getMessage("user.password_changed", new Object[]{user.getEmail()}, null));
            return new ModelAndView("redirect:/login");
        }
    }

}
