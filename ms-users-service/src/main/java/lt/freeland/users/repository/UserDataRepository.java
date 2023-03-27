package lt.freeland.users.repository;

import lt.freeland.common.domain.UserProfile;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

/**
 *
 * @author freeland
 */
public interface UserDataRepository extends DataTablesRepository<UserProfile, Long> {

    UserProfile findByUser_username(String username);

}
