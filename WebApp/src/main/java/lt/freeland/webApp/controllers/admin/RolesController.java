package lt.freeland.webApp.controllers.admin;

import javax.validation.Valid;
import lt.freeland.webApp.domain.RoleData;
import lt.freeland.webApp.domain.UserData;
import lt.freeland.webApp.services.RoleDataService;
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
@RequestMapping("/admin/roles")
public class RolesController {

    private final RoleDataService roleDataService;

    @Autowired
    public RolesController(RoleDataService roleDataService) {
        this.roleDataService = roleDataService;
    }

    @GetMapping(value = "/")
    public String usersList() {
        return "admin/roles";
    }

    @ResponseBody
    @PostMapping(value = "/find")
    public DataTablesOutput<RoleData> usersAll(@Valid @RequestBody DataTablesInput filter) {
        DataTablesOutput<RoleData> users = roleDataService.searchRoles(filter);
        return users;
    }
}
