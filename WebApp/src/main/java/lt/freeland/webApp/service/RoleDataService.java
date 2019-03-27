package lt.freeland.webApp.service;

import lt.freeland.common.domain.Role;
import lt.freeland.common.dto.RoleDto;
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

    public RoleDto findRoleById(Long uid) {
        RoleDto user = this.template.getForObject("http://users-service/roles/find/{id}", RoleDto.class, uid);
        return user;
    }

    public RoleDto[] findAll() {
        RoleDto[] roles = this.template.getForObject("http://users-service/roles/find/all", RoleDto[].class);
        return roles;
    }

    public DataTablesOutput<RoleDto> searchRoles(DataTablesInput filter) {
        HttpEntity<DataTablesInput> request = new HttpEntity(filter);
        ResponseEntity<DataTablesOutput<RoleDto>> response = this.template.exchange("http://users-service/roles/find/roles", HttpMethod.POST, request, new ParameterizedTypeReference<DataTablesOutput<RoleDto>>() {});
        DataTablesOutput<RoleDto> users = response.getBody();
        return users;
    }
}
