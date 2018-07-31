package lt.freeland.webApp.controllers.admin;

import java.util.List;
import lt.freeland.webApp.beans.UserDataDto;
import lt.freeland.webApp.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import lt.freeland.webApp.beans.DataTables;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping(value = "/all", params = {"draw"})
    @ResponseBody
    public DataTables usersAll(@RequestParam("draw") Integer draw) {
        List<UserDataDto> users = userDataService.findAll();
        DataTables dt = new DataTables();
        UserDataDto[] myArray = users.toArray(new UserDataDto[users.size()]);
        dt.setData(myArray);
        dt.setRecordsTotal(users.size());
        dt.setDraw(draw);
        return dt;
    }
}
