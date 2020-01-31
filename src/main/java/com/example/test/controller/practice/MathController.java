package com.example.test.controller.practice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/{operator}/{num1}/{key}/{num2}")
    @ResponseBody

    public int doMath(
            @PathVariable String operator,
            @PathVariable int num1,
            @PathVariable String key,
            @PathVariable int num2
    ) {
        if(operator.equals("divide"))
            if(num1 == 0 || num2 == 0)
                throw new ArithmeticException("Cannot divide 0");
        switch (operator.toLowerCase()){
            case "add": return num1 + num2;
            case "subtract": return num1 - num2;
            case "multiply": return num1 * num2;
            case "divide": return num1 / num2;
            default: return 0;
        }
    }
}