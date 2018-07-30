package lt.freeland.uaa;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UaaController {

    @Autowired
    DefaultTokenServices tokenServices;

    @Autowired
    private TokenStore tokenStore;

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

    @ResponseBody
    @PostMapping(value = "/invalidateToken")
    public Map<String, String> invalidateToken(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "access_token") String accessToken, Authentication authentication) {
        System.out.println("Invalidating access token :- " + accessToken);
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
        if (oAuth2AccessToken != null) {
            tokenStore.removeAccessToken(oAuth2AccessToken);
        }
        Map<String, String> ret = new HashMap<>();
        ret.put("removed_access_token", accessToken);
        return ret;
    }

    @GetMapping("/ssoLogout")
    public void ssoLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new SecurityContextLogoutHandler().logout(request, null, null);
        response.sendRedirect(request.getHeader("referer"));
    }
}
