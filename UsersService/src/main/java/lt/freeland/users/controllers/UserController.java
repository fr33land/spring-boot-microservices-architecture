package lt.freeland.users.controllers;

import java.util.List;
import javax.validation.Valid;
import lt.freeland.users.beans.UserProfile;
import lt.freeland.users.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    UserDataRepository userDataRepository;

    @GetMapping("/find/{uid}")
    public ResponseEntity<UserProfile> findUserById(@PathVariable("uid") Long uid) {
        return new ResponseEntity<>(userDataRepository.findByUserId(uid), HttpStatus.OK);
    }

    @GetMapping("/find/username/{username}")
    public ResponseEntity<UserProfile> findUserByUserName(@PathVariable("username") String username) {
        return new ResponseEntity<>(userDataRepository.findByUser_username(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/find/users", method = RequestMethod.POST)
    public DataTablesOutput<UserProfile> getUsers(@Valid @RequestBody DataTablesInput input) {
        return userDataRepository.findAll(input);
    }
}
