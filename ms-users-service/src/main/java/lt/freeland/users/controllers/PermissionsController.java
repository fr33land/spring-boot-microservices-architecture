package lt.freeland.users.controllers;

import javax.validation.Valid;
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
@RequestMapping("/permissions")
public class PermissionsController {

    //protected Logger logger = Logger.getLogger(UserController.class.getName());

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") Long id) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/find/permissions", method = RequestMethod.POST)
    public DataTablesOutput<?> getUsers(@Valid @RequestBody DataTablesInput input) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
}
