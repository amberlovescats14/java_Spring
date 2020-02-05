package com.example.test.controller.categories;

import com.example.test.exception.PostException;
import com.example.test.model.categories.Categories;
import com.example.test.repos.CategoriesRepo;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoriesController {
    CategoriesRepo categoriesDao;

    public CategoriesController(CategoriesRepo categoriesDao) {
        this.categoriesDao = categoriesDao;
    }

    @GetMapping("/categories")
    public String showCategories(Model model){
        List<Categories> categories = categoriesDao.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("category", new Categories());
        return "posts/categories";
    }

    @PostMapping("/categories")
    public String createCategories(
            @ModelAttribute Categories category
    ) {
        System.out.println(category.toString());
        categoriesDao.save(category);
        return "redirect:/categories";
    }

}
