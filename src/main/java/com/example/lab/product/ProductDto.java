package com.example.lab.product;

import com.example.lab.categories.Categories;

public record ProductDto(String title, String author, Categories bookCategory, String book_description, Double price) {
}
