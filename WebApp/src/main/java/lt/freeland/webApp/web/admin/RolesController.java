package lt.freeland.webApp.web.admin;

import javax.validation.Valid;
import lt.freeland.common.dto.RoleDto;
import lt.freeland.webApp.service.RoleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author freeland
 */
@Controller
@RequestMapping("/dashboard/admin/roles")
public class RolesController {

    private final RoleDataService roleDataService;

    @Autowired
    public RolesController(RoleDataService roleDataService) {
        this.roleDataService = roleDataService;
    }

    @ResponseBody
    @PostMapping(value = "/find")
    public DataTablesOutput<RoleDto> usersAll(@Valid @RequestBody DataTablesInput filter) {
        DataTablesOutput<RoleDto> roles = roleDataService.searchRoles(filter);
        return roles;
    }
}
