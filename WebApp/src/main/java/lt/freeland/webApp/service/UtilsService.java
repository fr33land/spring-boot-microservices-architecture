package lt.freeland.webApp.service;

import java.util.List;
import lt.freeland.common.entities.Countries;
import lt.freeland.webApp.repository.CountriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author freeland
 */
@Service
public class UtilsService {

    private final CountriesRepository countriesRepository;

    @Autowired
    public UtilsService(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @Cacheable(value = "countries")
    public List<Countries> getAllCountries() {
        return countriesRepository.findAll();
    }

}
