package com.example.test.controller;

import com.example.test.model.Product;
import com.example.test.repos.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class ProductController {

    //dependency injection
    private ProductRepository productDao;

    public ProductController(ProductRepository productDao) {
        this.productDao = productDao;
    }

//    end dependency injection

    //! this is how you would fetch from the front end
    @GetMapping("/products/jpa")
    @ResponseBody
    public List<Product> all() {
        return productDao.findAll();
    }

    @GetMapping("/products/jpa/{id}")
    @ResponseBody
    public Product getById(@PathVariable long id){
        return productDao.getOne(id);
    }

    //create: save()
    // will return a redirect
    @PostMapping("/products/jpa")
    public String createProduct(Product product){
        productDao.save(product);
        return "products";
    }

    //! created myself
    @GetMapping("/products/ordered")
    public List<Product> allOrderedByName(){
        return productDao.findAllByOrderByNameAsc();
    }

}
