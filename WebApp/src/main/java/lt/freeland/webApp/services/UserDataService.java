package lt.freeland.webApp.services;

import com.netflix.discovery.EurekaClient;
import lt.freeland.webApp.domain.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

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

    public UserData findUserById(Long uid) {
        UserData user = this.template.getForObject("http://users-service/users/find/{uid}", UserData.class, uid);
        return user;
    }

    public UserData findUserByUserName(String username) {
        UserData user = this.template.getForObject("http://users-service/users/find/username/{username}", UserData.class, username);
        return user;
    }

    public UserData[] findAll() {
        UserData[] users = this.template.getForObject("http://users-service/users/find/all", UserData[].class);
        return users;
    }

    public DataTablesOutput<UserData> searchUsers(DataTablesInput filter) {
        HttpEntity<DataTablesInput> request = new HttpEntity(filter);
        ResponseEntity<DataTablesOutput<UserData>> response = this.template.exchange("http://users-service/users/find/users", HttpMethod.POST, request, new ParameterizedTypeReference<DataTablesOutput<UserData>>() {});
        DataTablesOutput<UserData> users = response.getBody();
        return users;
    }
}
