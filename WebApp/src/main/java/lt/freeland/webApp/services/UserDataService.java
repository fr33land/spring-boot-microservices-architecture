package lt.freeland.webApp.services;

import lt.freeland.webApp.beans.UserDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDataDto user = this.template.getForObject("http://localhost:8082/users/find/{uid}", UserDataDto.class, uid);
        return user;
    }
}
