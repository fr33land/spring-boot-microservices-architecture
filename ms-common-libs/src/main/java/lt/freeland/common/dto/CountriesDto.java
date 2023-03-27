package lt.freeland.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author freeland
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountriesDto {

    private Integer id;
    private String name;
    private String isoCode;
    private String phoneCode;    
}
