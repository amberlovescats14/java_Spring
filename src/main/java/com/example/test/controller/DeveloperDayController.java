package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeveloperDayController {
    @GetMapping("/deimos/{days}")
    @ResponseBody

    public String displayDaysTillDeveloperDay(@PathVariable String days){
        return "There are " + days + " till Developer Day!";
    }


}
