package com.project.springbootwebstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("")
public class LoginController {

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
//        String referrer = request.getHeader("referer");
//        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx    " + referrer);
//        if (referrer != null) {
//            return "redirect:" + referrer; // redirect to the referrer URL
//        } else {
//            return "redirect:/home"; // redirect to a default URL if the referrer is null
//        }
        // get the referer URL from the request header
        String referer = request.getHeader("referer");
     System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx    " + referer);
        // redirect the user back to the previous page
//        return "redirect:" + referer;
        return "login.html";

    }

//    @GetMapping("/logout")
//    public String logout() {
//        return "log.html";
//
//    }
}
