package com.example.lab.product;

import com.example.lab.categories.Categories;
import lombok.Builder;

@Builder
public record ProductDto(String title, String author, String bookCategory, String book_description, Double price) {
}
