package lt.freeland.common.events;

import lombok.Value;
import lt.freeland.common.dto.ApplicationEventType;
import lt.freeland.common.domain.User;
import org.springframework.context.ApplicationEvent;

@Value
public class MailApplicationEvent extends ApplicationEvent {
    
    private final ApplicationEventType eventType;
    private final User user;
    private final String url;
    
    public MailApplicationEvent(Object source, ApplicationEventType eventType, User user, String url) {
        super(source);
        this.eventType = eventType;
        this.user = user;
        this.url = url;
    }       
}