package lt.freeland.users.controllers;

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
    public ResponseEntity<UserProfile> findUser(@PathVariable("uid") Long uid) {        
        return new ResponseEntity<>(userDataRepository.findByUserId(uid), HttpStatus.OK);
    }
    
}
