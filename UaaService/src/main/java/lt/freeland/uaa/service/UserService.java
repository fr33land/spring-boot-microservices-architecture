package lt.freeland.uaa.service;

import java.util.Optional;
import lt.freeland.common.dto.ApplicationEventType;
import lt.freeland.uaa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lt.freeland.common.events.MailApplicationEvent;
import lt.freeland.common.domain.PasswordResetToken;
import lt.freeland.common.domain.User;
import lt.freeland.uaa.repository.PasswordResetRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void updateUserPassword(User user, String tokenId) {
        PasswordResetToken token = getPasswordResetToken(tokenId);
        userRepository.setUserPassword(user.getPassword(), token.getUserId());
        passwordResetRepository.delete(token);
    }
}
