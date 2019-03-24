package lt.freeland.uaa.repository;

import lt.freeland.common.entities.UserProfile;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author freeland
 */
public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {

}
