package com.example.test.controller.post;

import com.example.test.exception.PostException;
import com.example.test.model.Post;
import com.example.test.model.Product;
import com.example.test.model.user.User;
import com.example.test.repos.PostRepo;
import com.example.test.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class PostController {

    private PostRepo postDao;
    private UserRepo userRepo;

    public PostController(PostRepo postDao, UserRepo userRepo) {
        this.postDao = postDao;
        this.userRepo = userRepo;
    }

    //! SHOW ALL
    @GetMapping("/posts")
    public String all(Model model){
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts";
    }
//z : react
//    @GetMapping("/posts")
//    @ResponseBody
//    public Iterable<Post> all(){
//
//        Iterable<Post> posts = postDao.findAll();
//        return posts;
//    }

    //! SHOW ONE
    @GetMapping("/posts/{id}")
    public String showUserById(
            @PathVariable long id,
            Model model
    ) throws PostException {
        Post found = postDao.findById(id)
                    .orElseThrow(()-> new PostException());

        model.addAttribute("post", found);
        return "singlePost";

    }

    //!CREATE
    @GetMapping("/posts/create")
    public String showForm( Model model){
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "create";
    }

    @PostMapping("/posts/create")
    public String createPost(
            @RequestParam(name = "title") String title,
            @RequestParam String description,
            @RequestParam String username,
            Model model
            ) {
        User user = getUserByUsername(username);
        Post post = new Post(title, description);
//        user.setPosts(post);
        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts";
    }

    private User getUserByUsername(String username){
        List<User> users = userRepo.findAll();
        User found = new User();
        for (User user : users) {
            if(user.getUsername().toLowerCase().equals(username.toLowerCase())){
                found.setUsername(username);
                found.setEmail(user.getEmail());
                found.setPassword(user.getPassword());
            }
        }
        return found;
    }

    //! EDIT
    @GetMapping("/posts/edit/{id}")
    public String showEdit(
            @PathVariable long id,
            Model model
    ) throws PostException {
        Post post = postDao.findById(id)
                .orElseThrow(()-> new PostException());
        model.addAttribute("post", post);
        return "edit";
    }

    @PostMapping("/posts/edit/{id}")
    public String editPost(
            @RequestParam long id,
            @RequestParam String title,
            @RequestParam String description,
            Model model
    ) {
        if(title.isEmpty() || description.isEmpty()){
            model.addAttribute("alert", true);
            return "redirect:/posts/edit/"+id;
        }
        Post post = new Post(id, title, description);
        postDao.save(post);
        return "redirect:/posts/"+id;

    }

    //! Delete
    @GetMapping("/posts/delete/{id}")
    public String showDelete(
            @PathVariable long id,
            Model model
    ) throws PostException {
        postDao.findById(id)
                .orElseThrow(()-> new PostException());
        model.addAttribute("id", id);
        return "delete";
    }

    @PostMapping("/posts/delete/{id}")
    public String deletePost(
            @PathVariable long id
    ) throws PostException {
        postDao.findById(id)
                .orElseThrow(()-> new PostException());
        postDao.deleteById(id);
        return "redirect:/posts";
    }



}
