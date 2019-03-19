package lt.freeland.webApp.service;

import lt.freeland.common.entities.Role;
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
public class RoleDataService {
    
    private final OAuth2RestTemplate template;

    @Autowired
    public RoleDataService(OAuth2RestTemplate template) {
        this.template = template;
    }

    public Role findRoleById(Long uid) {
        Role user = this.template.getForObject("http://users-service/roles/find/{id}", Role.class, uid);
        return user;
    }

    public Role[] findAll() {
        Role[] roles = this.template.getForObject("http://users-service/roles/find/all", Role[].class);
        return roles;
    }

    public DataTablesOutput<Role> searchRoles(DataTablesInput filter) {
        HttpEntity<DataTablesInput> request = new HttpEntity(filter);
        ResponseEntity<DataTablesOutput<Role>> response = this.template.exchange("http://users-service/roles/find/roles", HttpMethod.POST, request, new ParameterizedTypeReference<DataTablesOutput<Role>>() {});
        DataTablesOutput<Role> users = response.getBody();
        return users;
    }
}
