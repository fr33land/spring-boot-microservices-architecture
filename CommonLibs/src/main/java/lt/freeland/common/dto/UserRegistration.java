package lt.freeland.common.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lt.freeland.common.annotations.MatchingValue;
import lombok.Data;


/**
 *
 * @author freeland
 */
@Data
@MatchingValue(field = "password", matchingField = "passwordconfirm", message = "{password.not_match}")
public class UserRegistration {
    
    @NotEmpty(message = "{username.not_empty}")
    @Size(min = 5, message = "{username.min_length}")
    private String username;  
    
    @Email(message = "{email.not_valid}")
    @NotEmpty(message = "{email.not_empty}")
    @Size(min = 5, message = "{email.min_length}")
    private String email;
    
    @NotEmpty(message = "{password.not_empty}")
    @Size(min = 6, message = "{password.min_length}")
    private String password;
    
    @NotEmpty(message = "{password.not_empty}")
    @Size(min = 6, message = "{password.min_length}")
    private String passwordconfirm;          
    
}
