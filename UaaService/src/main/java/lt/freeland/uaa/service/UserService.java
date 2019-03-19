package lt.freeland.uaa.service;

import lt.freeland.common.domain.ApplicationEventType;
import lt.freeland.uaa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lt.freeland.common.events.MailApplicationEvent;
import lt.freeland.common.entities.PasswordResetToken;
import lt.freeland.common.entities.User;
import lt.freeland.uaa.repository.PasswordResetRepository;
import org.springframework.context.ApplicationEventPublisher;

/**
 *
 * @author freeland
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordResetRepository passwordResetRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public UserService(UserRepository userRepository, PasswordResetRepository passwordResetRepository, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.passwordResetRepository = passwordResetRepository;
        this.eventPublisher = eventPublisher;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email).orElse(null);
    }

    public void generateAndSendPasswordResetLink(User user, String appUrl) {
        eventPublisher.publishEvent(new MailApplicationEvent(this, ApplicationEventType.USER_PASSWORD_RESET, user, appUrl));
    }

    public PasswordResetToken getPasswordResetToken(String tokenId) {
        return passwordResetRepository.findByResetToken(tokenId).orElse(null);
    }

    public void updateUserPassword(User user, String tokenId) {
        passwordResetRepository.save(PasswordResetToken
                .builder()
                .resetToken(tokenId)
                .id(user.getUserId())
                .build()
        );
    }
}
