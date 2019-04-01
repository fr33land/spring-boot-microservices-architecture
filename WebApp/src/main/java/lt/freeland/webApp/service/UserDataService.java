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
import lt.freeland.common.dto.UserProfileDto;

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

    public UserProfileDto findUserById(Long uid) {
        UserProfileDto user = this.template.getForObject("http://users-service/users/find/{uid}", UserProfileDto.class, uid);
        return user;
    }

    public UserProfileDto findUserByUserName(String username) {
        UserProfileDto user = this.template.getForObject("http://users-service/users/find/username/{username}", UserProfileDto.class, username);
        return user;
    }

    public UserProfileDto[] findAll() {
        UserProfileDto[] users = this.template.getForObject("http://users-service/users/find/all", UserProfileDto[].class);
        return users;
    }

    public DataTablesOutput<UserProfileDto> searchUsers(DataTablesInput filter) {
        HttpEntity<DataTablesInput> request = new HttpEntity(filter);
        ResponseEntity<DataTablesOutput<UserProfileDto>> response = this.template.exchange("http://users-service/users/find/users", HttpMethod.POST, request, new ParameterizedTypeReference<DataTablesOutput<UserProfileDto>>() {});
        DataTablesOutput<UserProfileDto> users = response.getBody();
        return users;
    }
    
    public void saveUser(UserProfileDto userProfile) {
        HttpEntity<UserProfileDto> request = new HttpEntity(userProfile);
        ResponseEntity response = this.template.exchange("http://users-service/users/find/users", HttpMethod.PUT, request, new ParameterizedTypeReference<UserProfileDto>(){});
    }
}
