package lt.freeland.uaa.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import lt.freeland.common.domain.AccountActivation;
import lt.freeland.common.domain.Role;
import lt.freeland.common.domain.User;
import lt.freeland.uaa.repository.AccountActivationRepository;
import lt.freeland.uaa.repository.UserRepository;
import lt.freeland.uaa.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import lt.freeland.common.dto.Email;
import lt.freeland.uaa.beans.UserRegistrationForm;
import lt.freeland.uaa.exceptions.TokenExpiredException;
import lt.freeland.uaa.exceptions.TokenNotFoundException;
import lt.freeland.uaa.exceptions.UserNotFoundException;
import lt.freeland.uaa.exceptions.UserActivatedException;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;

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
    private final MessageSource messageSource;

    @Value("${spring.application.email}")
    private String senderEmail;

    @Autowired
    public UserRegistrationService(UserRepository userRepository, RoleRepository roleRepository, AccountActivationRepository accountActivationRepository, EmailService emailService, MessageSource messageSource) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.accountActivationRepository = accountActivationRepository;
        this.emailService = emailService;
        this.messageSource = messageSource;
    }

    public boolean checkIfUsernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean checkIfEmailExists(String email) {
        return userRepository.findByEmailIgnoreCase(email).isPresent();
    }

    public User registerUser(User user, String registrationUrl) {
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRoles(Arrays.asList(role));
        User newUser = userRepository.save(user);

        if (newUser.getUserId() != null) {
            generateAndSendActivation(user, registrationUrl);
        }

        return newUser;
    }

    public void generateAndSendActivation(User user, String appUrl) {
        AccountActivation accActivation = new AccountActivation();
        String token = UUID.randomUUID().toString();
        accActivation.setActivationToken(token);
        accActivation.setId(user.getUserId());
        accActivation.setExpireDate(LocalDateTime.now().plusHours(1));

        accountActivationRepository.save(accActivation);

        String message = appUrl + "/user/activation/confirm/" + token;

        Email approveEmail = new Email();
        approveEmail.setFrom(senderEmail);
        approveEmail.setTo(user.getEmail());
        approveEmail.setSubject("Portal registration confirmation");
        approveEmail.setTemplate("/email/templates/confirmAccountEmail");
        approveEmail.setMessage(message);

        MimeMessagePreparator messagePreparator = emailService.prepareEmailMessage(approveEmail);
        emailService.sendEmail(messagePreparator);

    }

    public boolean checkConfirmationTokenIsvalid(String token) throws TokenExpiredException, TokenNotFoundException {
        AccountActivation accToken = accountActivationRepository
                .findByActivationToken(token)
                .orElseThrow(() -> new TokenNotFoundException(messageSource.getMessage("user.confirmation_not_found", null, null)));

        if (accToken.getExpireDate().until(LocalDateTime.now(), ChronoUnit.HOURS) > 0) {
            throw new TokenExpiredException(messageSource.getMessage("user.confirmation_expired", null, null));
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public void confirmAccount(String token) throws TokenNotFoundException, UserNotFoundException {
        AccountActivation accToken = accountActivationRepository
                .findByActivationToken(token)
                .orElseThrow(() -> new TokenNotFoundException(messageSource.getMessage("user.confirmation_not_found", null, null)));

        User user = userRepository.findById(accToken.getId())
                .orElseThrow(() -> new UserNotFoundException(messageSource.getMessage("user.not_found_id", null, null)));
        user.setEnabled((short) 1);
        user.setEditedDate(LocalDateTime.now());

        userRepository.save(user);
        accountActivationRepository.delete(accToken);
    }

    public void generateAndSend(UserRegistrationForm user, String registrationUrl) throws UserActivatedException, UserNotFoundException {
        User uopt = userRepository
                .findByEmailIgnoreCase(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException(messageSource.getMessage("user.not_found", new Object[]{user.getEmail()}, null)));

        if (uopt.isEnabled()) {
            throw new UserActivatedException(messageSource.getMessage("user.already_activated", new Object[]{user.getEmail()}, null));
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setUserId(uopt.getUserId());

        generateAndSendActivation(newUser, registrationUrl);
    }
}
