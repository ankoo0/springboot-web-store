package com.project.springbootwebstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign-up")
public class SignupController {

    @GetMapping
    public String getSignUp(){
        return "signup.html";
    }
}
