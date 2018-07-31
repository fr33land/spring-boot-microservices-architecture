package lt.freeland.users.controllers;

import java.util.List;
import lt.freeland.users.beans.UserProfile;
import lt.freeland.users.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    
    @GetMapping("/find/all")
    public ResponseEntity<List<UserProfile>> findAll() {        
        return new ResponseEntity<>(userDataRepository.findAll(), HttpStatus.OK);
    }
    
}
