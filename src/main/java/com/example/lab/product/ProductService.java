package com.example.lab.product;

import com.example.lab.categories.Categories;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product>findAllProducts();
    List<Product> findAllProductsByCategory(Categories categories);
    Optional<Product> findAllProductsById(Long bookId);
    void deleteProduct(Long id);
    List<Product> findAllProductsByCategory(String categories);
    Product updateProduct(Long id, ProductUpdateRequest productUpdateRequest);
    Product addProduct(ProductCreationRequest productCreationRequest);
}
