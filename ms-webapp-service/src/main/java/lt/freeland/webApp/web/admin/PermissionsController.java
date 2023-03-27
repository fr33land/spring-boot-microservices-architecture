package lt.freeland.webApp.web.admin;

import javax.validation.Valid;
import lt.freeland.common.domain.UserProfile;
import lt.freeland.common.dto.UserProfileDto;
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
    private final UserDataService userDataService;

    public PermissionsController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @ResponseBody
    @PostMapping(value = "/find")
    public DataTablesOutput<UserProfileDto> usersAll(@Valid @RequestBody DataTablesInput filter) {
        DataTablesOutput<UserProfileDto> users = userDataService.searchUsers(filter);
        return users;
    }
}
