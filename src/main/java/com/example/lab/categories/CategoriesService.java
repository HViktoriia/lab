package com.example.lab.categories;

import com.example.lab.user.User;

import java.util.List;

public interface CategoriesService {
    List<Categories> findAllCategories();
    Categories addCategory(CategoriesCreationRequest categoriesCreationRequest);
}
