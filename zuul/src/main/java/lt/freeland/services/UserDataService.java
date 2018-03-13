package lt.freeland.services;

import lt.freeland.beans.UserDataDto;
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

    public UserDataDto findUser(Long uid) {
        String ats = this.template.getForObject("http://localhost:8080/users/find/{uid}", String.class, uid);
        return null;
    }
}
