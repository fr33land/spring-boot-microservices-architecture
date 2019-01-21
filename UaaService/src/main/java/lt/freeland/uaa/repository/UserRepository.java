package lt.freeland.uaa.repository;

import java.util.Optional;
import lt.freeland.common.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author freeland
 */
public interface UserRepository extends CrudRepository<User, Long> {

    public Optional<User> findByUsername(String username);

    public Optional<User> findByEmailIgnoreCase(String email);

}
