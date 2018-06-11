package lt.freeland.repository;

import lt.freeland.uaa.beans.User;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author freeland
 */
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);     
}