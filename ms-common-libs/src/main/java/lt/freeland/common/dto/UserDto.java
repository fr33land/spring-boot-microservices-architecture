package lt.freeland.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
@JsonIgnoreProperties({"userProfile", "password"})
public class UserDto {

    private Long userId;

    @NotEmpty
    @Size(min = 1, max = 45)
    private String username;

    @Email
    @NotEmpty
    @Size(min = 1, max = 255)
    private String email;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String password;

    private UserStatus status;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime editedDate;

    private Collection<RoleDto> roles;
    
    private UserProfileDto userProfile;
    
    public boolean isEnabled() {
        return status == UserStatus.ENABLED;
    }   
    
    public boolean isBlocked() {
        return status == UserStatus.BLOCKED;
    } 
}
