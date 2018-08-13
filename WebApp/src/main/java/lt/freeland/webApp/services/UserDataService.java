package lt.freeland.webApp.services;

import lt.freeland.datatables.filter.Filter;
import lt.freeland.webApp.beans.UserDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author freeland
 */
@Service
public class UserDataService {

    @Autowired
    private OAuth2RestTemplate template;

    public UserDataDto findUserById(Long uid) {
        UserDataDto user = this.template.getForObject("http://localhost:8082/users/find/{uid}", UserDataDto.class, uid);
        return user;
    }
    
    public UserDataDto findUserByUserName(String username) {
        UserDataDto user = this.template.getForObject("http://localhost:8082/users/find/username/{username}", UserDataDto.class, username);
        return user;
    }
    
    public UserDataDto[] findAll() {
        UserDataDto[] users = this.template.getForObject("http://localhost:8082/users/find/all", UserDataDto[].class);
        return users;
    }
    
    public UserDataDto[] searchUsers(Filter filter) {
        UserDataDto[] users = this.template.getForObject("http://localhost:8082/users/find/filtered", UserDataDto[].class, filter);
        return users;
    }
}
