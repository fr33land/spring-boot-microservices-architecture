package lt.freeland.webApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import lt.freeland.common.entities.UserProfile;

/**
 *
 * @author freeland
 */
@Service
public class UserDataService {

    private final OAuth2RestTemplate template;

    @Autowired
    public UserDataService(OAuth2RestTemplate template) {
        this.template = template;
    }

    public UserProfile findUserById(Long uid) {
        UserProfile user = this.template.getForObject("http://users-service/users/find/{uid}", UserProfile.class, uid);
        return user;
    }

    public UserProfile findUserByUserName(String username) {
        UserProfile user = this.template.getForObject("http://users-service/users/find/username/{username}", UserProfile.class, username);
        return user;
    }

    public UserProfile[] findAll() {
        UserProfile[] users = this.template.getForObject("http://users-service/users/find/all", UserProfile[].class);
        return users;
    }

    public DataTablesOutput<UserProfile> searchUsers(DataTablesInput filter) {
        HttpEntity<DataTablesInput> request = new HttpEntity(filter);
        ResponseEntity<DataTablesOutput<UserProfile>> response = this.template.exchange("http://users-service/users/find/users", HttpMethod.POST, request, new ParameterizedTypeReference<DataTablesOutput<UserProfile>>() {});
        DataTablesOutput<UserProfile> users = response.getBody();
        return users;
    }
}
