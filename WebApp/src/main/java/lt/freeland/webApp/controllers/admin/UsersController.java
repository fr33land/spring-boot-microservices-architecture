package lt.freeland.webApp.controllers.admin;

import javax.validation.Valid;
import lt.freeland.webApp.domain.UserData;
import lt.freeland.webApp.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    public UsersController(UserDataService userDataService) {
        this.userDataService = userDataService;
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
}
