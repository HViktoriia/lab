package com.example.lab.product;

import com.example.lab.categories.Categories;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
public final class ProductCreationRequest {

    @NotNull(message = "Title cannot be null")
    private final String title;

    @NotNull(message = "Author cannot be null")
    private final String author;

    @NotNull(message = "Category cannot be null")
    private final String categoryName;

    @NotNull(message = "Description cannot be null")
    private final String description;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be positive")
    private final Double price;

    @JsonCreator
    ProductCreationRequest(@JsonProperty("title") String title,
                           @JsonProperty("author") String author,
                           @JsonProperty("categoryName") String categoryName,
                           @JsonProperty("description") String description,
                           @JsonProperty("price") Double price){
        this.title = title;
        this.author = author;
        this.categoryName = categoryName;
        this.description = description;
        this.price = price;
    }
}
