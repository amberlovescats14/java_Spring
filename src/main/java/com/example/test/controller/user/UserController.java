package com.example.test.controller.user;

import com.example.test.exception.PostException;
import com.example.test.model.Post;
import com.example.test.model.user.User;
import com.example.test.repos.UserRepo;
import com.example.test.services.UserSvc;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private UserRepo userRepo;
    private UserRepo userDao;
    private PasswordEncoder passwordEncoder;
    private String userAnon = "anonymousUser";
    private UserSvc userSvc;



    public UserController(
            UserRepo userDao,
            PasswordEncoder passwordEncoder,
            UserSvc userSvc
    ) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.userSvc = userSvc;
    }



    @GetMapping("/users")
    public String showIndex(Model model){
        long loggedInId = -1;
        if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals(userAnon)) {
            User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            loggedInId = user.getId();
        }

        List<User> users = userDao.findAll();
        model.addAttribute("users", users);
        model.addAttribute("loggedInId", loggedInId);
        return "users/all";
    }

    @GetMapping("/user/{id}")
    public String showSingleUser(
            @PathVariable long id,
            Model model
    ) throws PostException {
        User found = userDao.findById(id)
                .orElseThrow(()-> new PostException());
        if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals(userAnon)){
            User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(userSvc.checkIfCurrentUserMatchesParam(currentUser, found)){
                model.addAttribute("options", true);
                model.addAttribute("user", found);
                return "users/single";
            }
        }
        model.addAttribute("options", false);
        model.addAttribute("user", found);
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
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
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

        if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals(userAnon)){
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(userSvc.checkIfCurrentUserMatchesParam(currentUser,user)){
                model.addAttribute("user", user);
                return "users/edit";
            }
        }
        return "redirect:/users";
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
        User foundUser = userDao.findById(id)
                .orElseThrow(()->new PostException());
        if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals(userAnon)){
            User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(userSvc.checkIfCurrentUserMatchesParam(currentUser, foundUser)){
                userDao.delete(foundUser);
                return "redirect:/users";
            }
        }
        return "redirect:/users";
    }



}
