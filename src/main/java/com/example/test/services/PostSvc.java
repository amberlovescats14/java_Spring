package com.example.test.services;

import com.example.test.model.Post;
import com.example.test.model.categories.Categories;
import com.example.test.model.user.User;
import com.example.test.repos.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostSvc {
    private UserRepo userDao;

    public PostSvc(UserRepo userDao) {
        this.userDao = userDao;
    }

    //!Get user by username
    public User getUserByUsername(String username){
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

    //! Add user to categories
    public void addPostToCategories(Post post){
        System.out.println("AddPostToCategories");
        for (Categories category : post.getCategories()) {
            category.addPost(post);
        }
    }
}
