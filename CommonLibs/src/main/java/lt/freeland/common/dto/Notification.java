package lt.freeland.common.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lt.freeland.common.domain.User;


/**
 *
 * @author freeland
 */
@Data
@Builder
public class Notification {    
        
    private String ip;
    private String agent;
    private LocalDateTime time;    
    private User user;
    private ApplicationEventType eventType;
}
