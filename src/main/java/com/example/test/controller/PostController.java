package com.example.test.controller;

import com.example.test.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {
    private ArrayList<Post> posts = new ArrayList<Post>(){{
        add(new Post(1,"First"));
        add(new Post(2,"Second"));
        add(new Post(3,"Third"));
    }};


    @GetMapping("/posts")
    @ResponseBody
    public ArrayList<Post> all(){
        for (Post post : posts) {
            System.out.println(post);
        }
        return posts;
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
    @ResponseBody
    public String showForm(){
        return "create";
    }

}
