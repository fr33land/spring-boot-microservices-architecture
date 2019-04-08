package lt.freeland.common.exceptions;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 *
 * @author freeland
 */
@Getter
public class ApiError {

    private HttpStatus status;
    
    private Date timestamp;
    
    private String message;
    
    private List<String> errors;
    
    private String path;

    private ApiError() {
        timestamp = new Date();
    }

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiError(HttpStatus status, Throwable ex) {
        this(status);
        this.message = ex.getLocalizedMessage();
    }
    
    public ApiError(HttpStatus status, String message) {
        this(status);
        this.status = status;
        this.message = message;
    }
    
    public ApiError(HttpStatus status, String message, String error) {
        this(status, message);
        this.errors = Arrays.asList(error);
    }

    public ApiError(HttpStatus status, String message, List<String> errors) {
        this(status, message);
        this.errors = errors;
    }
    
    public ApiError(HttpStatus status, String message, List<String> errors, String path) {
        this(status, message, errors);
        this.path = path;
    }
    
    public ApiError(HttpStatus status, String message, String error, String path) {
        this(status, message, Arrays.asList(error), path);
    }
}
