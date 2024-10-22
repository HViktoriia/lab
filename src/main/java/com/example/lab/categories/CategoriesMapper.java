package com.example.lab.categories;

import org.springframework.stereotype.Component;

@Component
public class CategoriesMapper {
    public CategoriesDto categoriesDto (Categories categories){
        return new CategoriesDto(categories.getName(), categories.getDescription());
    }
}
