package com.example.test.services;

import org.springframework.stereotype.Service;

@Service
public class SillySvc {

    public String sayHi(){
        System.out.println("SILLY HI");
        return "Hi";
    }

}
