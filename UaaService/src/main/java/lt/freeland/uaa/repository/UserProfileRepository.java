package lt.freeland.uaa.repository;

import lt.freeland.common.domain.UserProfile;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author freeland
 */
public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {

}
