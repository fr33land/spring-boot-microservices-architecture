package lt.freeland.webApp.repository;

import lt.freeland.common.domain.Countries;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author freeland
 */
public interface CountriesRepository extends JpaRepository<Countries, Integer> {
    
}
