package lt.freeland.webApp.web.admin;

import javax.validation.Valid;
import lt.freeland.common.domain.UserData;
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
@RequestMapping("/admin/permissions")
public class PermissionsController {

    @Autowired
    UserDataService userDataService;

    @ResponseBody
    @PostMapping(value = "/find")
    public DataTablesOutput<UserData> usersAll(@Valid @RequestBody DataTablesInput filter) {
        DataTablesOutput<UserData> users = userDataService.searchUsers(filter);
        return users;
    }
}
