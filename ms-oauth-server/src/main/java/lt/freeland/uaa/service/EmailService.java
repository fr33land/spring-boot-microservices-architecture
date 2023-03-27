package lt.freeland.uaa.service;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import lt.freeland.common.dto.Email;

/**
 *
 * @author r.sabaliauskas
 */
@Slf4j
@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendEmail(MimeMessagePreparator email) {
        mailSender.send(email);
    }

    public MimeMessagePreparator prepareEmailMessage(Email email) {

        return (mimeMessage) -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(email.getFrom());
            messageHelper.setTo(email.getTo());
            messageHelper.setSubject(email.getSubject());
            String content = processEmailMessage(email.getMessage(), email.getTemplate());
            messageHelper.setText(content, true);
        };

    }

    private String processEmailMessage(Map<String, Object> variables, String template) {
        Context context = new Context();
        context.setVariables(variables);
        return templateEngine.process(template, context);
    }
    
    public void prepareAndSend(Email email) {
        MimeMessagePreparator messagePreparator = prepareEmailMessage(email);
        sendEmail(messagePreparator);
    }
}
