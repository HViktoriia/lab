package com.example.lab.product;

import com.example.lab.categories.Categories;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
public class ProductUpdateRequest {
    private Long productId;

    private String title;

    private String author;

    private Categories bookCategory;

    private String book_description;
    private Long categoryId;

    private Double price;

    public ProductUpdateRequest(
            @JsonProperty("title") String title,
            @JsonProperty("author") String author,
            @JsonProperty("bookCategory") Categories bookCategory,
            @JsonProperty("book_description") String book_description,
            @JsonProperty("price") Double price) {
        this.title = title;
        this.author = author;
        this.bookCategory = bookCategory;
        this.book_description = book_description;
        this.price = price;
    }

}
