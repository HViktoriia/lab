package com.example.lab.categories;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/categories")
@RequiredArgsConstructor
public class CategoriesController {

    private CategoriesMapper categoriesMapper;
    private final CategoriesService categoriesService;

    @GetMapping
    public List<CategoriesDto> getAllCategories(){
        return categoriesService.findAllCategories()
                .stream()
                .map(categories -> new CategoriesDto(
                        categories.getName(),
                        categories.getDescription()))
                        .toList();
    }
}
