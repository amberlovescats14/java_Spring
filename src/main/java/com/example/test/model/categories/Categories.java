package com.example.test.model.categories;

import com.example.test.model.Post;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categories {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Post> posts;

    public Categories() {
    }

    public Categories(String name){
        this.name = name;
    }

    public void addPost(Post post){
        posts.add(post);
        System.out.println("added");
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
