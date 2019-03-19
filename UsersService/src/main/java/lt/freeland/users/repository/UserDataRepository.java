package lt.freeland.users.repository;

import lt.freeland.common.entities.UserProfile;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

/**
 *
 * @author freeland
 */
public interface UserDataRepository extends DataTablesRepository<UserProfile, Long> {

    UserProfile findByUser_username(String username);

}
