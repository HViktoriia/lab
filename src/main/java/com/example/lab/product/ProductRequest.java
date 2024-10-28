package com.example.lab.product;

import com.example.lab.categories.Categories;

public record ProductRequest(String title, String author, Categories categoryName, String bookDescription, Double price) {
}
