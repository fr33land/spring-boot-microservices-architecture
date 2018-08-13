package lt.freeland.webApp.controllers.admin;

import java.util.List;
import lt.freeland.datatables.data.Data;
import lt.freeland.datatables.filter.Filter;
import lt.freeland.webApp.beans.UserDataDto;
import lt.freeland.webApp.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping(value = "/all")
    public Data usersAll(@RequestBody Filter filter) {
        UserDataDto[] users = userDataService.searchUsers(filter);
        Data dt = new Data();
        dt.setData(users);
        dt.setRecordsTotal(users.length);
        dt.setRecordsFiltered(users.length);
        dt.setDraw(filter.getDraw());
        return dt;
    }
}
