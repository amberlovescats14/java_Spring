package com.example.test.controller.user;

import com.example.test.model.Post;
import com.example.test.model.user.User;
import com.example.test.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {
    UserRepo userDao;

    public UserController(UserRepo userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/users")
    public String showIndex(Model model){
        List<User> users = userDao.findAll();
        model.addAttribute("users", users);
        return "users/all";
    }

    @GetMapping("/user/{id}")
    public String showSingleUser(
            @PathVariable long id,
            Model model
    ){
        return "users/single";
    }
}
