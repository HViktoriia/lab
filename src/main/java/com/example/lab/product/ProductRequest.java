package com.example.lab.product;

public record ProductRequest(String title, String author, Long categoryId, String bookDescription, Double price) {
}
