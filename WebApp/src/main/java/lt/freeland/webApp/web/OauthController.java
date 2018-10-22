package lt.freeland.webApp.web;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author freeland
 */
@RestController
public class OauthController {

    @GetMapping(value = "/oauthCallback", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> callBack(@RequestParam Map<String, String> reqParam) {
        return new ResponseEntity(reqParam, HttpStatus.OK);
    }

}
