package com.example.lab.product;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto productDto(Product product){
        return new ProductDto(product.getTitle(), product.getAuthor(), product.getBookCategory(), product.getBook_description(), product.getPrice());
    }
}
