package lt.freeland.uaa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import lt.freeland.common.dto.Email;

/**
 *
 * @author r.sabaliauskas
 */
@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendEmail(MimeMessagePreparator email) {
        mailSender.send(email);
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
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

    private String processEmailMessage(String message, String template) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process(template, context);
    }
}
