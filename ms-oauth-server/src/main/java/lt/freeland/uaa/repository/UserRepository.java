package lt.freeland.uaa.repository;

import java.util.Optional;
import lt.freeland.common.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author freeland
 */
public interface UserRepository extends CrudRepository<User, Long> {

    public Optional<User> findByUsername(String username);

    public Optional<User> findByEmailIgnoreCase(String email);

    @Modifying
    @Query("update User u set u.password = :password WHERE u.id = :userId")
    void setUserPassword(@Param("password") String pasword, @Param("userId") Long userId);

}
