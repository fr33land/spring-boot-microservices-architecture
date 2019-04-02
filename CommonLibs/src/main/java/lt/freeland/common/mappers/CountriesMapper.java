package lt.freeland.common.mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lt.freeland.common.domain.Countries;
import lt.freeland.common.dto.CountriesDto;

/**
 *
 * @author freeland
 */
public class CountriesMapper {

    public List<CountriesDto> countriesToDtoMapper(List<Countries> countries) {
        return countries
                .stream()
                .map(this::countryToDtoMapper)
                .collect(Collectors.toList());

    }
    
    public CountriesDto countryToDtoMapper(Countries country) {
        return Optional
                .of(country)
                .map(c -> CountriesDto
                                    .builder()
                                    .id(c.getId())
                                    .isoCode(c.getIsoCode())
                                    .name(c.getName())
                                    .phoneCode(c.getPhoneCode())
                                    .build()
                )
                .orElse(null);

    }
}
