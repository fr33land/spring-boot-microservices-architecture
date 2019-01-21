package lt.freeland.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author r.sabaliauskas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    
    private String from;
    
    private String to;
    
    private String subject;
    
    private String address;
    
    private String message;
    
    private String template;

}
