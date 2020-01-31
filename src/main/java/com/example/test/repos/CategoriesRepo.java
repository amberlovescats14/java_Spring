package com.example.test.repos;

import com.example.test.model.categories.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepo extends JpaRepository<Categories, Long> {
}
