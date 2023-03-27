package lt.freeland.common.events;

import lombok.Value;
import org.springframework.context.ApplicationEvent;
import lt.freeland.common.dto.Notification;

@Value
public class NotificationEvent extends ApplicationEvent {
    
    private final Notification notification;
    
    public NotificationEvent(Object source, Notification notification) {
        super(source);
        this.notification = notification;
    }       
}