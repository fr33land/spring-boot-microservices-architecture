package lt.freeland.users.controllers;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lt.freeland.common.domain.UserProfile;
import lt.freeland.common.dto.UserProfileDto;
import lt.freeland.users.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author freeland
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserDataRepository userDataRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserDataRepository userDataRepository, ModelMapper modelMapper) {
        this.userDataRepository = userDataRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/find/{uid}")
    public ResponseEntity findUserById(@PathVariable("uid") Long uid) {
        return userDataRepository.findById(uid)
                .map(u -> ResponseEntity.ok(u))
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id [" + uid + "] not found"));  
    }

    @GetMapping("/find/username/{username}")
    public ResponseEntity findUserByUserName(@PathVariable("username") String username) {
        return Optional
                .ofNullable(userDataRepository.findByUser_username(username))
                .map(user -> ResponseEntity.ok(user))
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "User username [" + username + "] not found"));      
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/find/users")
    public ResponseEntity<DataTablesOutput<UserProfile>> getUsers(@RequestBody DataTablesInput input) {
        return Optional
                .ofNullable(userDataRepository.findAll(input))
                .map(data -> ResponseEntity.ok(data))
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "User list is empty"));    
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/find/users")
    public ResponseEntity saveUser(@RequestBody UserProfileDto userProfileDto) {
        return userDataRepository.findById(userProfileDto.getUserId())
                .map(u -> ResponseEntity.ok(
                        userDataRepository.save(
                                modelMapper.map(userProfileDto, UserProfile.class)
                        )
                ))
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST, "User id:" + userProfileDto.getUserId() + " bad request"));  
    }
}
