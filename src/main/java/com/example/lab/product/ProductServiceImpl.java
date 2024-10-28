package com.example.lab.product;

import com.example.lab.categories.Categories;
import com.example.lab.categories.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final CategoriesRepository categoriesRepository;

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
//    @Override
//    public List<Product> findAllProductsByCategoryId(Long categoryId){
//        return productRepository.findByCategoryId(categoryId);
//    }
    @Override
    public Optional<Product> findAllProductsById(Long bookId){
        return productRepository.findById(bookId);
    }

    @Override
    public List<Product> findAllProductsByCategory(String categories) {
        return List.of();
    }

    @Override
    public Product addProduct(ProductCreationRequest request){
        Product product = new Product(
                request.getTitle(),
                request.getAuthor(),
                request.getCategoryName(),
                request.getDescription(),
                request.getPrice()
        );
        return productRepository.save(product);
    }
}
