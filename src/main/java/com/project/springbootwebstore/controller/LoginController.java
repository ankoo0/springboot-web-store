package com.project.springbootwebstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login.html";

    }

//    @GetMapping("/logout")
//    public String logout() {
//        return "log.html";
//
//    }
}
