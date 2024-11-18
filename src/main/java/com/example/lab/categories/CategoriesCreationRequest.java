package com.example.lab.categories;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoriesCreationRequest {

    private Long categoriesId;

    @NotNull(message = "Name cannot be null")
    private String name;
    private String description;

    public CategoriesCreationRequest(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }
}
