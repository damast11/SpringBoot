package com.example.dkylish.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
//
//    @GetMapping("/login")
//    public String loginPage() {
//        return "Login pls";
//    }

}
