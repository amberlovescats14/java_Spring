package com.example.test.controller.user;

import com.example.test.model.Post;
import com.example.test.model.user.User;
import com.example.test.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        User user = users.get(1);
        List<Post> posts = user.getPosts();
        for (Post post : posts) {
            System.out.println(post.getTitle());
            System.out.println(post.getId());
            System.out.println(post.getUser().getId());
            System.out.println("-------");
        }
        model.addAttribute("users", users);
        return "users/all";
    }
}
