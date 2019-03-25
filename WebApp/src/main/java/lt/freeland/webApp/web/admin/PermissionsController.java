package lt.freeland.webApp.web.admin;

import javax.validation.Valid;
import lt.freeland.common.entities.UserProfile;
import lt.freeland.webApp.service.UserDataService;
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
@RequestMapping("/dashboard/admin/permissions")
public class PermissionsController {

    @Autowired
    UserDataService userDataService;

    @ResponseBody
    @PostMapping(value = "/find")
    public DataTablesOutput<UserProfile> usersAll(@Valid @RequestBody DataTablesInput filter) {
        DataTablesOutput<UserProfile> users = userDataService.searchUsers(filter);
        return users;
    }
}
