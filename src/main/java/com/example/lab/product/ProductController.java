package com.example.lab.product;

import com.example.lab.categories.Categories;
import com.example.lab.categories.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/products")
@RequiredArgsConstructor
public class ProductController {
    private ProductMapper productMapper;
    private final ProductService productService;
    private final CategoriesRepository categoriesRepository;

    @PostMapping("/add")
    public Product addProduct(@RequestBody ProductRequest productRequest){
        Categories category = categoriesRepository.findById(productRequest.categoryId())
                .orElseThrow(() -> new RuntimeException("Category doesn't exist"));
        Product newProduct = new Product(
                productRequest.title(),
                productRequest.author(),
                category,
                productRequest.bookDescription(), productRequest.price()
        );
        return productService.saveProduct(newProduct);
    }

    @GetMapping
    public List<ProductDto> getAllProducts(){
        return productService.findAllProducts()
                .stream()
                .map(product -> new ProductDto(
                        product.getTitle(),
                        product.getAuthor(),
                        product.getBookCategory().getCategories_id(),
                        product.getBook_description(),
                        product.getPrice()))
                .toList();
    }

    @GetMapping("/category/{categoryName}")
    public List<Product> getProductByCategory(@PathVariable String categoryName){
        Categories category = categoriesRepository.findByName(categoryName);
        return productService.findAllProductsByCategory(category);
    }
}
