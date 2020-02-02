package com.example.test.controller.user;

import com.example.test.exception.PostException;
import com.example.test.model.Post;
import com.example.test.model.user.User;
import com.example.test.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    ) throws PostException {
        User user = userDao.findById(id)
                .orElseThrow(()-> new PostException());
        model.addAttribute("user", user);
        return "users/single";
    }

    //! CREATE
    @GetMapping("/user/create")
    public String showCreateView(Model model){
        model.addAttribute("user", new User());
        return "users/create";
    }

    @PostMapping("/user/create")
    public String createUser(
            @ModelAttribute User user
    ) {
        userDao.save(user);
        return "redirect:/user/"+user.getId();
    }

    //!EDIT
    @GetMapping("/user/edit/{id}")
    public String showEditView(
            @PathVariable long id,
            Model model
    ) throws PostException {
        User user = userDao.findById(id)
                .orElseThrow(()-> new PostException());
        model.addAttribute("user", user);
        return "users/edit";
    }
    @PostMapping("/user/edit/{id}")
    public String editUser(
            @ModelAttribute User user,
            @PathVariable long id
    ){
        userDao.save(user);
        return "redirect:/user/" + id;
    }

    //! DELETE

    @PostMapping("/user/delete/{id}")
    public String deleteUser(
            @PathVariable long id
    ) throws PostException {
        User user = userDao.findById(id)
                .orElseThrow(()->new PostException());
        userDao.delete(user);
        return "redirect:/users";
    }



}
