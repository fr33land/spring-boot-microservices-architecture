package lt.freeland.common.dto;


/**
 *
 * @author r.sabaliauskas
 */
public enum ApplicationEventType {
    
    USER_REGISTRATION("User registration event"),
    USER_ACTIVATION("User activation event"),
    USER_PASSWORD_RESET("User password reset event");
    
    String title;

    private ApplicationEventType(String title) {
        this.title = title;
    }
    
}