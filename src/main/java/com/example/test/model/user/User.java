package com.example.test.model.user;

import com.example.test.model.Post;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "varchar(100) not null")
    private String username;

    @Column(columnDefinition = "varchar(100) not null")
    private String email;

    @Column(columnDefinition = "varchar(100) not null")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;

    public User() {
    }
    public User(
            String username,
            String email,
            String password
    ){
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User(
            String username,
            String email,
            String password,
            List<Post> posts
    ){
        this.username = username;
        this.email = email;
        this.password = password;
        this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
