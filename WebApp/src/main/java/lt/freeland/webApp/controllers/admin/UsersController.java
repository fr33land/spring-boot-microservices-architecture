package lt.freeland.webApp.controllers.admin;

import java.util.List;
import javax.validation.Valid;
import lt.freeland.webApp.beans.UserDataDto;
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

    @Autowired
    UserDataService userDataService;

    @GetMapping(value = "/")
    public String usersList() {
        return "admin/users";
    }

    @ResponseBody
    @PostMapping(value = "/find")
    public DataTablesOutput usersAll(@Valid @RequestBody DataTablesInput filter) {
        DataTablesOutput users = userDataService.searchUsers(filter);
        return users;
    }
}
