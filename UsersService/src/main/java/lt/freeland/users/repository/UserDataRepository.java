package lt.freeland.users.repository;

import lt.freeland.users.beans.UserProfile;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

/**
 *
 * @author freeland
 */
public interface UserDataRepository extends DataTablesRepository<UserProfile, Long> {
    
    UserProfile findByUserId(Long uid);
    UserProfile findByUser_username(String username);
    
}
