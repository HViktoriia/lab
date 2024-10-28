package com.example.lab.product;

import java.util.*;
import java.util.function.*;
import com.example.lab.categories.Categories;
import com.example.lab.categories.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/products")
@RequiredArgsConstructor
public class ProductController {
    private ProductMapper productMapper;
    private final ProductService productService;
    private final CategoriesRepository categoriesRepository;


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createNewProduct(@RequestBody
                                       @Validated ProductCreationRequest productCreationRequest){
        Categories category;
        Optional<Categories> optionalCategories = Optional.ofNullable(categoriesRepository.findByName(String.valueOf(productCreationRequest.getCategoryName())));
        if (optionalCategories.isPresent()){
            category = optionalCategories.get();
        } else {
            category = new Categories(String.valueOf(productCreationRequest.getCategoryName()), "Opis");
            category = categoriesRepository.save(category);
        }

        Product newProduct = new Product(
                productCreationRequest.getTitle(),
                productCreationRequest.getAuthor(),
                productCreationRequest.getCategoryName(),
                productCreationRequest.getDescription(),
                productCreationRequest.getPrice()
        );
        Product savedProduct = productService.saveProduct(newProduct);
    return productMapper.productDto(newProduct);
    }

    @GetMapping
    public List<ProductDto> getAllProducts(){
        return productService.findAllProducts()
                .stream()
                .map(productMapper::productDto)
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"/category"})
    public List<ProductDto> getProductsByCategory(@RequestParam String category){
        return productService.findAllProductsByCategory(category).stream()
                .map(productMapper::productDto)
                .collect(Collectors.toList());
    }

//    @GetMapping(params = {"categoryId"})
//    public List<ProductDto> getProductsByCategoryId(@RequestParam Long categoryId) {
//        return productService.findAllProductsByCategoryId(categoryId).stream()
//                .map(productMapper::productDto)
//                .collect(Collectors.toList());
//    }
    @GetMapping(params = {"bookId"})
    public List<ProductDto> getProductsById(@RequestParam Long bookId){
        return productService.findAllProductsById(bookId).stream()
                .map(productMapper::productDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category/{categoryName}")
    public List<Product> getProductByCategory(@PathVariable String categoryName){
        Categories category = categoriesRepository.findByName(categoryName);
        return productService.findAllProductsByCategory(category);
    }
}
