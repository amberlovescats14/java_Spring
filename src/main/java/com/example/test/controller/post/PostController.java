package com.example.test.controller.post;

import com.example.test.exception.PostException;
import com.example.test.model.Post;
import com.example.test.model.categories.Categories;
import com.example.test.model.user.User;
import com.example.test.repos.CategoriesRepo;
import com.example.test.repos.PostRepo;
import com.example.test.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {

    private PostRepo postDao;
    private UserRepo userDao;
    private CategoriesRepo categoriesDao;

    public PostController(PostRepo postDao, UserRepo userDao, CategoriesRepo categoriesDao) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.categoriesDao = categoriesDao;
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
        List<User> users = userDao.findAll();
        List<Categories> categories = categoriesDao.findAll();
        Post post = new Post();
        model.addAttribute("users", users);
        model.addAttribute("allCategories", categories);
        model.addAttribute("post", post);
        return "create";
    }

    @PostMapping("/posts/create")
    public String createPost(
            @ModelAttribute Post post,
            @RequestParam String[] primitiveCategories,
            @RequestParam String username
            ) {
        User user = getUserByUsername(username);
        post.setUser(user);

        List<Categories> listOfCategories = new ArrayList<>();
        for (String cat : primitiveCategories) {
            System.out.println("caata");
            listOfCategories.add(new Categories(cat));
        }
        post.setCategories(listOfCategories);


        Post p2 =  postDao.save(post);
        return "redirect:/posts/" +p2.getId() ;
    }

    private User getUserByUsername(String username){
        List<User> users = userDao.findAll();
        User found = new User();
        for (User user : users) {
            if(user.getUsername().toLowerCase().equals(username.toLowerCase())){
                found.setId(user.getId());
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
            @ModelAttribute Post post,
            @RequestParam long userId,
            Model model
    ) throws PostException {
        User user = userDao.findById(userId)
                .orElseThrow(()->new PostException());
        post.setUser(user);
        Post updatedPost = postDao.save(post);
        return "redirect:/posts/"+updatedPost.getId();

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
