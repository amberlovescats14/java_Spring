package com.example.test.repos;

import com.example.test.model.Post;
import com.example.test.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {

}
