package vn.hcmute.projectmanagement.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v1/users")
public class UserController {
    @GetMapping("/")
    public String main(){
        return "hello all";
    }

    @GetMapping("/user")
    public String user(){
        return "permit user";
    }
    @RequestMapping(value = "/admin", //
            method = RequestMethod.GET)
    public String admin(){
        return "permit admin";
    }
}
