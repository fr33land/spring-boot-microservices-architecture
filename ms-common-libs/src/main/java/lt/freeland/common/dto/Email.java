package lt.freeland.common.dto;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author r.sabaliauskas
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    
    private String from;    
    private String to;    
    private String subject;    
    private String address;    
    private Map<String, Object> message;    
    private String template;

}
