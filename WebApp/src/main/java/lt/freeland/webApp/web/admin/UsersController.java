package lt.freeland.webApp.web.admin;

import java.util.List;
import lt.freeland.webApp.service.UtilsService;
import javax.validation.Valid;
import lt.freeland.common.domain.UserData;
import lt.freeland.common.entities.Countries;
import lt.freeland.webApp.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author freeland
 */
@Controller
@RequestMapping("/admin/users")
public class UsersController {

    private final UserDataService userDataService;
    private final UtilsService utilsService;

    @Autowired
    public UsersController(UserDataService userDataService, UtilsService utilsService) {
        this.userDataService = userDataService;
        this.utilsService = utilsService;
    }

    @GetMapping(value = "/")
    public String usersList() {
        return "admin/users";
    }

    @ResponseBody
    @PostMapping(value = "/find")
    public DataTablesOutput<UserData> usersAll(@Valid @RequestBody DataTablesInput filter) {
        DataTablesOutput<UserData> users = userDataService.searchUsers(filter);
        return users;
    }
    
    @ResponseBody
    @GetMapping(value = "/find/{id}")
    public ResponseEntity<UserData> findUserById(@PathVariable("id") Long id){                
        return ResponseEntity.ok(userDataService.findUserById(id));
    }
    
    @ResponseBody
    @GetMapping(value = "/countries")
    public ResponseEntity<List<Countries>> findAllCountries(){                
        return ResponseEntity.ok(utilsService.getAllCountries());
    }
}
