package com.example.lab.product;

import com.example.lab.categories.Categories;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product>findAllProducts();
    List<Product> findAllProductsByCategory(Categories categories);
}
