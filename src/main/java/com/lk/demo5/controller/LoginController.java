package com.lk.demo5.controller;


import com.lk.demo5.utils.HttpResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.LinkedHashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/loginceshi")
public class LoginController {

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public HttpResult doPost(@RequestBody Object data) throws IOException {
        LinkedHashMap linkedHashMap = (LinkedHashMap) data;
        System.out.println(linkedHashMap.get("mobileNumber"));
        System.out.println(linkedHashMap.get("code"));

//        return "login";
        return HttpResult.ok("login");
    }
}
