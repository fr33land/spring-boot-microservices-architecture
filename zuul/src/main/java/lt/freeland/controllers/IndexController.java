/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.freeland.controllers;

import lt.freeland.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author freeland
 */
@Controller
public class IndexController {
    
    @Autowired
    UserDataService userDataService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        userDataService.findUser(2L);
        return "index";
    }
}
