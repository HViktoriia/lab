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
    public Product updateProduct(Long id, ProductUpdateRequest productUpdateRequest){
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with ID " + id + " does not exist"));
        if (productUpdateRequest.getTitle() != null) {
            existingProduct.setTitle(productUpdateRequest.getTitle());
        }
        if (productUpdateRequest.getAuthor() != null) {
            existingProduct.setAuthor(productUpdateRequest.getAuthor());
        }
        if (productUpdateRequest.getCategoryId() != null) {
            Categories category = categoriesRepository.findById(productUpdateRequest.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category with ID " + productUpdateRequest.getCategoryId() + " does not exist"));
            existingProduct.setBookCategory(category);
        }
        if (productUpdateRequest.getBook_description() != null) {
            existingProduct.setBook_description(productUpdateRequest.getBook_description());
        }
        if (productUpdateRequest.getPrice() != null) {
            existingProduct.setPrice(productUpdateRequest.getPrice());
        }

        return productRepository.save(existingProduct);

    }

    @Override
    public void deleteProduct(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product with ID " + id + " not found"));
        productRepository.delete(product);
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
