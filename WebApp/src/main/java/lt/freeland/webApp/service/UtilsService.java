package lt.freeland.webApp.service;

import java.util.List;
import lt.freeland.common.domain.Countries;
import lt.freeland.common.dto.CountriesDto;
import lt.freeland.common.mappers.CountriesMapper;
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
    private final CountriesMapper countryMapper = new CountriesMapper();

    @Autowired
    public UtilsService(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @Cacheable(value = "countries")
    public List<CountriesDto> getAllCountries() {
        List<Countries> countries = countriesRepository.findAll();
        return countryMapper.countriesToDtoMapper(countries);
    }    

}
