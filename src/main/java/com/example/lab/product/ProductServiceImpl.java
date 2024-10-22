package com.example.lab.product;

import com.example.lab.categories.Categories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }
    @Override
    public List<Product>findAllProductsByCategory(Categories categories){
        return productRepository.findByBookCategory(categories);
    }
}
