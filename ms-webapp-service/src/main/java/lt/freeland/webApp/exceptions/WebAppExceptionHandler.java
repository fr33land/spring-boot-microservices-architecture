package lt.freeland.webApp.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lt.freeland.common.exceptions.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author freeland
 */
@ControllerAdvice
@AllArgsConstructor
public class WebAppExceptionHandler {
    
    private final ObjectMapper mapper;

    @ExceptionHandler({HttpServerErrorException.class, HttpClientErrorException.class})
    public ModelAndView handleHttpStatusCodeException(HttpStatusCodeException e, HttpServletResponse response) throws IOException {
        HashMap apiErrorMap = mapper.readValue(e.getResponseBodyAsString(), HashMap.class);  
        ApiError apiError = new ApiError(
                HttpStatus.valueOf(Integer.parseInt(apiErrorMap.get("status").toString())), 
                apiErrorMap.get("message").toString(), 
                apiErrorMap.get("error").toString(),
                apiErrorMap.get("path").toString()
        );
        return new ModelAndView("error", "error", apiError);
    }

}
