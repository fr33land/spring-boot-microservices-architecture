package lt.freeland.uaa.service;

import java.util.Arrays;
import java.util.UUID;
import lt.freeland.common.domain.AccountActivation;
import lt.freeland.common.domain.Role;
import lt.freeland.common.domain.User;
import lt.freeland.uaa.repository.AccountActivationRepository;
import lt.freeland.uaa.repository.UserRepository;
import lt.freeland.uaa.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author freeland
 */
@Service
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AccountActivationRepository accountActivationRepository;
    private final EmailService emailService;
    private final TemplateEngine templateEngine;

    @Autowired
    public UserRegistrationService(UserRepository userRepository, RoleRepository roleRepository, AccountActivationRepository accountActivationRepository, EmailService emailService, TemplateEngine templateEngine) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.accountActivationRepository = accountActivationRepository;
        this.emailService = emailService;
        this.templateEngine = templateEngine;
    }

    public boolean checkIfUsernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean checkIfEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Transactional(rollbackFor = Exception.class)
    public User registerUser(User user) {
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRoles(Arrays.asList(role));
        return userRepository.save(user);
    }

    public void generateAndSendActivation(User user, String appUrl) {
        AccountActivation accActivation = new AccountActivation();
        String token = UUID.randomUUID().toString();
        accActivation.setActivationToken(token);
        accActivation.setId(user.getUserId());

        accountActivationRepository.save(accActivation);

        String message = appUrl + "/confirm/" + token;

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("info@lku.lt");
            messageHelper.setTo(user.getEmail());
            messageHelper.setSubject("User registration confirmation");
            String content = prepareAccountActivationEmail(message);
            messageHelper.setText(content, true);
        };

        emailService.sendEmail(messagePreparator);

    }

    private String prepareAccountActivationEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("/email/accountActivationTemplate", context);
    }

}
