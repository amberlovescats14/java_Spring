package com.example.test.controller.user;

import com.example.test.repos.UserRepo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class UserController {
    UserRepo userDao;

    public UserController(UserRepo userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/all-users")
    public String showUserIndex(Model model){

        return "/users/all";
    }


}
