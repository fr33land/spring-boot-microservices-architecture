package lt.freeland.uaa.exceptions;

/**
 *
 * @author r.sabaliauskas
 */
public class UserActivatedException extends RuntimeException{
    
    public UserActivatedException() {
        super();
    }

    public UserActivatedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserActivatedException(final String message) {
        super(message);
    }
    
}
