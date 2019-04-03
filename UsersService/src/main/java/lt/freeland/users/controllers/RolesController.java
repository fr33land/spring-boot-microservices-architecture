package lt.freeland.users.controllers;

import java.util.Optional;
import javax.validation.Valid;
import lt.freeland.common.domain.Role;
import lt.freeland.users.repository.RolesRepository;
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
@RequestMapping("/roles")
public class RolesController {

    //protected Logger logger = Logger.getLogger(UserController.class.getName());
    private final RolesRepository rolesRepository;

    @Autowired
    public RolesController(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Role> findRoleById(@PathVariable("id") Long id) {
        return Optional
                .ofNullable(rolesRepository.findById(id).get())
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/find/roles", method = RequestMethod.POST)
    public DataTablesOutput<Role> getRoles(@Valid @RequestBody DataTablesInput input) {
        DataTablesOutput<Role> data = rolesRepository.findAll(input);
        return data;
    }
}
