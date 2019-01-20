package lt.freeland.uaa.beans;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 *
 * @author freeland
 */
public class UserDataTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<>();
//        additionalInfo.put("firstName", user.getUserProfile().getFirstName());
//        additionalInfo.put("lastName", user.getUserProfile().getLastName());
//        additionalInfo.put("userAddress", user.getUserProfile().getAddress());
//        additionalInfo.put("city", user.getUserProfile().getCity());
//        additionalInfo.put("country", user.getUserProfile().getNationality().getName());
//        additionalInfo.put("countryCode", user.getUserProfile().getNationality().getIsoCode());
//        additionalInfo.put("phone", user.getUserProfile().getPhone());
//        additionalInfo.put("birthday", user.getUserProfile().getBirthday());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
