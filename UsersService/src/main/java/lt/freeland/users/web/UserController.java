package lt.freeland.users.web;

import java.util.Optional;
import javax.validation.Valid;
import lt.freeland.common.domain.UserProfile;
import lt.freeland.common.exceptions.EntityNotFoundException;
import lt.freeland.common.exceptions.EntityListEmptyException;
import lt.freeland.users.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author freeland
 */
@RestController
@RequestMapping("/users")
public class UserController {

    //protected Logger logger = Logger.getLogger(UserController.class.getName());
    private final UserDataRepository userDataRepository;

    @Autowired
    public UserController(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @GetMapping("/find/{uid}")
    public ResponseEntity<UserProfile> findUserById(@PathVariable("uid") Long uid) {
        return userDataRepository.findById(uid)
                .map(user -> ResponseEntity.ok(user))
                .orElseThrow(() -> new EntityNotFoundException(UserProfile.class, "uid", uid));
    }

    @GetMapping("/find/username/{username}")
    public ResponseEntity<UserProfile> findUserByUserName(@PathVariable("username") String username) {
        return Optional
                .ofNullable(userDataRepository.findByUser_username(username))
                .map(user -> ResponseEntity.ok(user))
                .orElseThrow(() -> new EntityNotFoundException(UserProfile.class, "username", username));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/find/users", method = RequestMethod.POST)
    public ResponseEntity<DataTablesOutput<UserProfile>> getUsers(@Valid @RequestBody DataTablesInput input) {
        return Optional
                .ofNullable(userDataRepository.findAll(input))
                .map(data -> ResponseEntity.ok(data))
                .orElseThrow(() -> new EntityListEmptyException(UserProfile.class));
    }
}
