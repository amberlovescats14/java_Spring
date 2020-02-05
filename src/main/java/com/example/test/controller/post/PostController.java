package com.example.test.controller.post;

import com.example.test.exception.PostException;
import com.example.test.model.Post;
import com.example.test.model.categories.Categories;
import com.example.test.model.user.User;
import com.example.test.repos.CategoriesRepo;
import com.example.test.repos.PostRepo;
import com.example.test.repos.UserRepo;
import com.example.test.services.EmailService;
import com.example.test.services.PostSvc;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//<th:errors="*{title}"
//@ModelAttribute @Valid Post post

@Controller
public class PostController {

    private PostRepo postDao;
    private UserRepo userDao;
    private CategoriesRepo categoriesDao;
    private PostSvc postSvc;
    private EmailService emailService;

    public PostController(PostRepo postDao, UserRepo userDao, CategoriesRepo categoriesDao, PostSvc postSvc, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.categoriesDao = categoriesDao;
        this.postSvc = postSvc;
        this.emailService = emailService;
    }

    //! SHOW ALL
    @GetMapping("/posts")
    public String all(Model model){
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/posts";
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
        return "posts/singlePost";

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
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(
            @ModelAttribute Post post,
            @RequestParam Long[] primitiveCategories,
            @RequestParam String username
            ) throws PostException {
        User user = postSvc.getUserByUsername(username);
        post.setUser(user);

        for (Long cat : primitiveCategories) {
          Categories category = categoriesDao.findById(cat)
                  .orElseThrow(()-> new PostException());
          post.addCategory(category);
        }
        Post p2 =  postDao.save(post);
        postSvc.addPostToCategories(p2);
//        z: emailService
        emailService.prepareAndSend(
                p2,
                "Post Created",
                p2.getUser().getUsername().toUpperCase()+ " created a new " +
                "post with the title: " + p2.getTitle().toUpperCase());
//        end email

        return "redirect:/posts/" +p2.getId() ;
    }



    //! EDIT
    @GetMapping("/posts/edit/{id}")
    public String showEdit(
            @PathVariable long id,
            Model model
    ) throws PostException {
        Post post = postDao.findById(id)
                .orElseThrow(()-> new PostException());

        //creating an arrayList of id's for check purpose
        List<Long> catIDs = new ArrayList<>();
        for (Categories category : post.getCategories()) {
            catIDs.add(category.getId());
        }
        model.addAttribute("catIds", catIDs);
        model.addAttribute("post", post);
        model.addAttribute("allCategories", categoriesDao.findAll());
        return "posts/edit";
    }

    @PostMapping("/posts/edit/{id}")
    public String editPost(
            @ModelAttribute Post post,
            @RequestParam long userId,
            @RequestParam Long[] primitiveCategories,
            Model model
    ) throws PostException {
        User user = userDao.findById(userId)
                .orElseThrow(()->new PostException());
        post.setUser(user);
        for (Long catID : primitiveCategories) {
            Categories category = categoriesDao.findById(catID)
                    .orElseThrow(()-> new PostException());
            post.addCategory(category);
        }
        Post updatedPost = postDao.save(post);
        emailService.prepareAndSend(updatedPost, "Updated Post", "'"+updatedPost.getTitle()+"' has been updated");
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
        return "posts/delete";
    }

    @PostMapping("/posts/delete/{id}")
    public String deletePost(
            @PathVariable long id
    ) throws PostException {
        Post found =  postDao.findById(id)
                .orElseThrow(()-> new PostException());
        emailService.prepareAndSend(found, "Post deleted", "Post '"+found.getTitle()+"' has been delted.");
        postDao.deleteById(id);
        return "redirect:/posts";
    }



}
