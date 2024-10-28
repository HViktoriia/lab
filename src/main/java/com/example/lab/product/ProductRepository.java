package com.example.lab.product;

import com.example.lab.categories.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product>findByBookCategory(Categories categories);
    //List<Product>findByCategoryId(Long categoryId);

}
