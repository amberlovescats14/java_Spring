package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private String username;

    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String authLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {
        System.out.println(username);
        System.out.println(password);
        if(username.equals("admin"))
            if(password.equals("password")) {
                this.username = username;
                System.out.println("yes");
                model.addAttribute("username", username);
                return "profile";
            }
        model.addAttribute("alert", true);
            return "login";

    }

    @GetMapping("/profile")
    public String showProfile(Model model){
        model.addAttribute("username", username);
        return "profile";
    }
}
