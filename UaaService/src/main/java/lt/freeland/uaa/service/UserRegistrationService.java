package lt.freeland.uaa.service;

import lt.freeland.uaa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author freeland
 */
@Service
public class UserRegistrationService {

    UserRepository userRepository;

    @Autowired
    public UserRegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkIfUsernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean checkIfEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}
