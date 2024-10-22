package com.example.lab.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/products")
@RequiredArgsConstructor
public class ProductController {
    private ProductMapper productMapper;
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts(){
        return productService.findAllProducts()
                .stream()
                .map(product -> new ProductDto(
                        product.getTitle(),
                        product.getAuthor(),
                        product.getBook_category().getCategories_id(),
                        product.getBook_description(),
                        product.getPrice()))
                .toList();
    }
}
