package lt.freeland.common.utils;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author freeland
 */
public class Utils {

    public static String getAppUrl(HttpServletRequest request) {
        String uri =  UriComponentsBuilder
                .newInstance()
                .scheme(request.getScheme())
                .host(request.getServerName())
                .port(request.getServerPort())
                .path(request.getContextPath())
                .build()
                .normalize()
                .toString();
        
        return uri;
    }
}
