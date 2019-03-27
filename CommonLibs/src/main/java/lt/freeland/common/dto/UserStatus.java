package lt.freeland.common.dto;

/**
 *
 * @author freeland
 */
public enum UserStatus {
    
    DISABLED("User disabled"),
    ENABLED("User enabled"),
    BLOCKED("User blocked");
    
    private final String title;
    
    UserStatus(String title) {
        this.title = title;
    }
    
    public String getTitle(){
        return this.title;
    }
    
}
