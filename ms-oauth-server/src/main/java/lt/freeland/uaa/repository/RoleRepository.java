package lt.freeland.uaa.repository;

import lt.freeland.common.domain.Role;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author freeland
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    
    Role findByName(String name);
}
