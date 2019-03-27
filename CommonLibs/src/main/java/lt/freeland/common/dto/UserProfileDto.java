package lt.freeland.common.dto;

import java.time.LocalDate;
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
public class UserProfileDto {
    
    private Long userId;    
    private String firstName;
    private String lastName;    
    private LocalDate birthday;
    private String city;
    private String address;
    private String phone;    
    private CountriesDto nationality;    
    private UserDto user;    
}
