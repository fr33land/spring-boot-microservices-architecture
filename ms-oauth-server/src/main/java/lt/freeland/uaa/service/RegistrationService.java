package lt.freeland.uaa.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import lt.freeland.common.dto.ApplicationEventType;
import lt.freeland.common.dto.UserStatus;
import lt.freeland.common.domain.AccountActivationToken;
import lt.freeland.common.domain.Role;
import lt.freeland.common.domain.User;
import lt.freeland.common.domain.UserProfile;
import lt.freeland.uaa.repository.AccountActivationRepository;
import lt.freeland.uaa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lt.freeland.uaa.beans.UserRegistration;
import lt.freeland.common.events.MailApplicationEvent;
import lt.freeland.uaa.exceptions.TokenExpiredException;
import lt.freeland.uaa.exceptions.TokenNotFoundException;
import lt.freeland.uaa.exceptions.UserNotFoundException;
import lt.freeland.uaa.exceptions.UserActivatedException;
import lt.freeland.uaa.repository.RoleRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author freeland
 */
@Service
public class RegistrationService {

    private final AccountActivationRepository accountActivationRepository;
    private final MessageSource messageSource;
    private final ApplicationEventPublisher eventPublisher;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(AccountActivationRepository accountActivationRepository, MessageSource messageSource, ApplicationEventPublisher eventPublisher, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.accountActivationRepository = accountActivationRepository;
        this.messageSource = messageSource;
        this.eventPublisher = eventPublisher;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean checkIfUsernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
    
    public boolean checkIfEmailExists(String email) {
        return userRepository.findByEmailIgnoreCase(email).isPresent();
    }

    @Transactional(rollbackFor = Exception.class)
    public User registerUser(UserRegistration user, String registrationUrl) {
        User newUser = createUser(user);

        if (newUser.getUserId() != null) {
            eventPublisher.publishEvent(new MailApplicationEvent(this, ApplicationEventType.USER_REGISTRATION, newUser, registrationUrl));
//            eventPublisher.publishEvent(new NotificationEvent(this, Notification.builder()
//                    .ip(request.getHeader("X-Forwarded-For") == null ? request.getRemoteAddr() : request.getHeader("X-Forwarded-For"))
//                    .agent(request.getHeader("User-Agent"))
//                    .time(LocalDateTime.now())
//                    .user(newUser)
//                    .eventType(ApplicationEventType.USER_REGISTRATION).build())
//            );
        }

        return newUser;
    }
    
    public User createUser(UserRegistration user) {        
        User newUser = new User();
        UserProfile profile = new UserProfile();
        Role userRole = roleRepository.findByName("ROLE_USER");
        
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail().toLowerCase());
        newUser.setStatus(UserStatus.DISABLED);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setCreatedDate(LocalDateTime.now());        
        newUser.setRoles(Arrays.asList(userRole));       
        newUser.setUserProfile(profile);
        profile.setUser(newUser);
        
        newUser = userRepository.save(newUser);               
        
        return newUser;
    }

    public boolean checkConfirmationTokenIsvalid(String token) throws TokenExpiredException, TokenNotFoundException {
        AccountActivationToken accToken = accountActivationRepository
                .findByActivationToken(token)
                .orElseThrow(() -> new TokenNotFoundException(messageSource.getMessage("user.confirmation_not_found", null, null)));

        if (accToken.getExpireDate().until(LocalDateTime.now(), ChronoUnit.HOURS) > 0) {
            throw new TokenExpiredException(messageSource.getMessage("user.confirmation_expired", null, null));
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public void confirmAccount(String token) throws TokenNotFoundException, UserNotFoundException {
        AccountActivationToken accToken = accountActivationRepository
                .findByActivationToken(token)
                .orElseThrow(() -> new TokenNotFoundException(messageSource.getMessage("user.confirmation_not_found", null, null)));

        User user = userRepository.findById(accToken.getUserId())
                .orElseThrow(() -> new UserNotFoundException(messageSource.getMessage("user.not_found_id", null, null)));
        user.setStatus(UserStatus.ENABLED);
        user.setEditedDate(LocalDateTime.now());

        userRepository.save(user);
        accountActivationRepository.delete(accToken);
    }

    public void generateAndSend(UserRegistration user, String registrationUrl) throws UserActivatedException, UserNotFoundException {
        User uopt = userRepository
                .findByEmailIgnoreCase(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException(messageSource.getMessage("user.not_found", new Object[]{user.getEmail()}, null)));

        if (uopt.isEnabled()) {
            throw new UserActivatedException(messageSource.getMessage("user.already_activated", new Object[]{user.getEmail()}, null));
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setUserId(uopt.getUserId());

        eventPublisher.publishEvent(new MailApplicationEvent(this, ApplicationEventType.USER_ACTIVATION, newUser, registrationUrl));
    }
}
