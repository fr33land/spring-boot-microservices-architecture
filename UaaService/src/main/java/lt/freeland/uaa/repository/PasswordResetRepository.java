package lt.freeland.uaa.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import lt.freeland.common.domain.PasswordResetToken;

/**
 *
 * @author r.sabaliauskas
 */
public interface PasswordResetRepository extends CrudRepository<PasswordResetToken, Long>{
    
    public Optional<PasswordResetToken> findByResetToken(String tokenId);
    
}
