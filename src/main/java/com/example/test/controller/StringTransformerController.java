package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StringTransformerController {
    @GetMapping("/string/reverse/{string}")
    @ResponseBody
    public String reverseString(@PathVariable String string){
        return new StringBuilder(string).reverse().toString();
    }

    @GetMapping("/string/uppercase/{string}")
    @ResponseBody
    public String toUpperCase(@PathVariable String string){
        return string.toUpperCase();
    }

    @GetMapping("/string/both/string")
    @ResponseBody
    public String bothReverseAndUppercase(@PathVariable String string){
        return new StringBuilder(string).reverse().toString().toUpperCase();
    }

    @GetMapping("/string/both/{string}")
    @ResponseBody
    public String queryBoth(
            @PathVariable String string,
            @RequestParam (required = true) Boolean reverse,
            @RequestParam (required = true) Boolean caps
            ){
        if(reverse && caps) {
            return new StringBuilder(string).reverse().toString().toUpperCase();
        }
        if(reverse) return reverseString(string);
        if(caps) return toUpperCase(string);
        else return string;

    }



}
