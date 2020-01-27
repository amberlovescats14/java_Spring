package com.example.test.controller;

import com.example.test.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {
    private ArrayList<Post> posts = new ArrayList<>(){{
        add(new Post(1,"First"));
        add(new Post(2,"Second"));
        add(new Post(3,"Third"));
    }};


    @GetMapping("/posts")
    public String all(Model model){
        model.addAttribute("posts", posts);
        return "posts";
    }


    @GetMapping("/posts/{id}")
    @ResponseBody
    public String showUserById(@PathVariable long id){
        String response = "User Not found";
        for (Post post : posts) {
            if(post.getId() == id) {
                response = post.toString();
            }
        }
        return response;
    }

    @GetMapping("/posts/create")
    public String showForm(){
        return "create";
    }

    @PostMapping("/posts/create")
    public String createPost(
            @RequestParam(name = "title") String title,
            Model model
            ) {
        posts.add(new Post(posts.size()+1, title));
        model.addAttribute("posts", posts);
        return "posts";
    }


}
