package lt.freeland.webApp.web.admin;

import java.util.List;
import lt.freeland.webApp.service.UtilsService;
import lt.freeland.common.domain.Countries;
import lt.freeland.common.domain.UserProfile;
import lt.freeland.common.dto.CountriesDto;
import lt.freeland.common.dto.UserProfileDto;
import lt.freeland.webApp.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author freeland
 */
@Controller
@RequestMapping("/dashboard/admin/users")
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
        return "/dashboard/admin/users/list";
    }

    @GetMapping(value = "/edit/{userId}")
    public ModelAndView userEdit(@PathVariable("userId") Long userId, ModelMap mm) {
        UserProfileDto userProfile = userDataService.findUserById(userId);
        mm.addAttribute("userProfile", userProfile);
        mm.addAttribute("countriesList", utilsService.getAllCountries());
        return new ModelAndView("/dashboard/admin/users/edit", mm);
    }
    
    @PostMapping(value = "/save")
    public ModelAndView userSave(@ModelAttribute("userProfile") UserProfileDto userProfile) {
        userDataService.saveUser(userProfile);
        return new ModelAndView("/dashboard/admin/users/list");
    }

    @ResponseBody
    @PostMapping(value = "/list")
    public DataTablesOutput<UserProfileDto> getUsersList(@RequestBody DataTablesInput filter) {
        DataTablesOutput<UserProfileDto> users = userDataService.searchUsers(filter);
        return users;
    }

    @ResponseBody
    @GetMapping(value = "/find/{id}")
    public ResponseEntity<UserProfileDto> findUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userDataService.findUserById(id));
    }

    @ResponseBody
    @GetMapping(value = "/countries")
    public ResponseEntity<List<CountriesDto>> findAllCountries() {
        return ResponseEntity.ok(utilsService.getAllCountries());
    }
}
