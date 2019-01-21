package lt.freeland.uaa.repository;

import java.util.Optional;
import lt.freeland.common.domain.AccountActivation;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author r.sabaliauskas
 */
public interface AccountActivationRepository extends CrudRepository<AccountActivation, Long> {

    Optional<AccountActivation> findByActivationToken(String token);
}
