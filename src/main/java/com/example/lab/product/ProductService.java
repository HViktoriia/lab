package com.example.lab.product;

import com.example.lab.categories.Categories;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product>findAllProducts();
    List<Product> findAllProductsByCategory(Categories categories);
    List<Product> findAllProductsByCategory(String categories);
    Optional<Product> findAllProductsById(Long id);
    //List<Product>findAllProductsByCategoryId(Long id);

    Product addProduct(ProductCreationRequest productCreationRequest);
}
