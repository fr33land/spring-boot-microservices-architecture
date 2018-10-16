package lt.freeland.webApp.services;

import com.netflix.discovery.EurekaClient;
import lt.freeland.webApp.domain.RoleData;
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
public class RoleDataService {
    
    private final OAuth2RestTemplate template;

    @Autowired
    public RoleDataService(OAuth2RestTemplate template) {
        this.template = template;
    }

    public RoleData findRoleById(Long uid) {
        RoleData user = this.template.getForObject("http://users-service/roles/find/{id}", RoleData.class, uid);
        return user;
    }

    public RoleData[] findAll() {
        RoleData[] roles = this.template.getForObject("http://users-service/roles/find/all", RoleData[].class);
        return roles;
    }

    public DataTablesOutput<RoleData> searchRoles(DataTablesInput filter) {
        HttpEntity<DataTablesInput> request = new HttpEntity(filter);
        ResponseEntity<DataTablesOutput<RoleData>> response = this.template.exchange("http://users-service/roles/find/roles", HttpMethod.POST, request, new ParameterizedTypeReference<DataTablesOutput<RoleData>>() {});
        DataTablesOutput<RoleData> users = response.getBody();
        return users;
    }
}
