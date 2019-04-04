package lt.freeland.webApp.exceptions;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
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
public class WebAppExceptionHandler {

    @ExceptionHandler({HttpServerErrorException.class, HttpClientErrorException.class})
    public ModelAndView handleHttpStatusCodeException(HttpStatusCodeException e, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("error", e.getStatusCode());
        model.setViewName("error");
        return model;
    }

}
