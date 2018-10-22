package lt.freeland.repository;

import lt.freeland.common.domain.User;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author freeland
 */
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);     
}