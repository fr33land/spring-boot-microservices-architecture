package lt.freeland.users.repository;

import lt.freeland.common.entities.Role;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

/**
 *
 * @author freeland
 */
public interface RolesRepository extends DataTablesRepository<Role, Long> {
    
}
