package lt.freeland.uaa.service;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import lt.freeland.common.domain.AccountActivation;
import lt.freeland.common.domain.User;
import lt.freeland.uaa.repository.AccountActivationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author r.sabaliauskas
 */
@Slf4j
@Service
public class AccountActivationService {

    private final AccountActivationRepository accountActivationRepository;
    private final EmailService emailService;
    private final TemplateEngine templateEngine;

    @Autowired
    public AccountActivationService(AccountActivationRepository accountActivationRepository, EmailService emailService, TemplateEngine templateEngine) {
        this.accountActivationRepository = accountActivationRepository;
        this.emailService = emailService;
        this.templateEngine = templateEngine;
    }

    public void generateAndSendApproveLink(User user, String appUrl) {
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
        return templateEngine.process("confirmAccountEmail", context);
    }
}
