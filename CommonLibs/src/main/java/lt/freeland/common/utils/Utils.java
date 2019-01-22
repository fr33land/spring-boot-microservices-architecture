package lt.freeland.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author freeland
 */
public class Utils {
    
    public static String getAppUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + (request.getServerPort() != 80 ? ":" + request.getServerPort() : "") + (request.getContextPath().isEmpty() ? "" : request.getContextPath());
    }
}
