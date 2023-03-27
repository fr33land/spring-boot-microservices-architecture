package lt.freeland.uaa.events.listeners;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import lt.freeland.common.dto.Email;
import lt.freeland.common.events.MailApplicationEvent;
import lt.freeland.common.domain.AccountActivationToken;
import lt.freeland.common.domain.PasswordResetToken;
import lt.freeland.common.domain.User;
import lt.freeland.uaa.repository.AccountActivationRepository;
import lt.freeland.uaa.repository.PasswordResetRepository;
import lt.freeland.uaa.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MailApplicationEventListeners {

    private final EmailService emailService;
    private final AccountActivationRepository accountActivationRepository;
    private final PasswordResetRepository passwordResetRepository;

    @Value("${spring.application.email}")
    private String senderEmail;

    @Autowired
    public MailApplicationEventListeners(EmailService emailService, AccountActivationRepository accountActivationRepository, PasswordResetRepository passwordResetRepository) {
        this.emailService = emailService;
        this.accountActivationRepository = accountActivationRepository;
        this.passwordResetRepository = passwordResetRepository;
    }

    @Async
    @EventListener(condition = "#event.eventType == T(lt.freeland.common.dto.ApplicationEventType).USER_REGISTRATION")
    void handleUserRegistrationEmail(MailApplicationEvent event) {
        generateAndSendApproveLink(event.getUser(), event.getUrl());
    }

    @Async
    @EventListener(condition = "#event.eventType == T(lt.freeland.common.dto.ApplicationEventType).USER_ACTIVATION")
    void handleActivationLinkEmail(MailApplicationEvent event) {
        generateAndSendApproveLink(event.getUser(), event.getUrl());
    }

    @Async
    @EventListener(condition = "#event.eventType == T(lt.freeland.common.dto.ApplicationEventType).USER_PASSWORD_RESET")
    void handlePasswordResetLinkEmail(MailApplicationEvent event) {
        generatePasswordAndSendResetLink(event.getUser(), event.getUrl());
    }

    private void generateAndSendApproveLink(User user, String appUrl) {
        AccountActivationToken accActivation = AccountActivationToken
                .builder()
                .activationToken(UUID.randomUUID().toString().replace("-", ""))
                .userId(user.getUserId())
                .expireDate(LocalDateTime.now().plusHours(1))
                .build();

        String message = appUrl + "/user/activation/confirm/" + accActivation.getActivationToken();

        accountActivationRepository.save(accActivation);
        emailService.prepareAndSend(Email
                .builder()
                .from(senderEmail)
                .to(user.getEmail())
                .subject("Portal registration confirmation")
                .template("/email/templates/confirmAccountEmail")
                .message(Map.of("message", message))
                .build()
        );
    }

    private void generatePasswordAndSendResetLink(User user, String appUrl) {
        PasswordResetToken passwordResetToken = PasswordResetToken
                .builder()
                .resetToken(UUID.randomUUID().toString().replace("-", ""))
                .userId(user.getUserId())
                .expireDate(LocalDateTime.now().plusHours(1))
                .build();
        
        String message = appUrl + "/user/password/reset/" + passwordResetToken.getResetToken();

        passwordResetRepository.save(passwordResetToken);
        emailService.prepareAndSend(Email
                .builder()
                .from(senderEmail)
                .to(user.getEmail())
                .subject("Portal password reset")
                .template("/email/templates/resetPasswordEmail")
                .message(Map.of("message", message))
                .build()
        );
    }
}
