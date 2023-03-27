package lt.freeland.uaa.repository;

import java.util.Optional;
import lt.freeland.common.domain.AccountActivationToken;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author r.sabaliauskas
 */
public interface AccountActivationRepository extends CrudRepository<AccountActivationToken, Long> {

    Optional<AccountActivationToken> findByActivationToken(String token);
}
