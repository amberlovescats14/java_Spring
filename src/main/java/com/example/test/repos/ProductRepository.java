package com.example.test.repos;

import com.example.test.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//TAKES in model and primary key type
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByNameAsc();

    Product findByName(String name);

    //!specific query  HQL :JPQL
    @Query("select name from Product where length(name) > 10 ")
    List<String> getProductsOfACertainNameLength();

    @Query(nativeQuery = true, value = "select name from Products where length(title) > 10")
    List<String> nativeQuery();

}
