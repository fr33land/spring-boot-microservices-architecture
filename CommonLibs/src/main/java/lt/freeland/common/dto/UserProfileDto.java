package lt.freeland.common.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    
    private String city;
    
    private String address;
    
    private String phone;  
    
    private CountriesDto nationality;  
    
    private UserDto user;    
}
