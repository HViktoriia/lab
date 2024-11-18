package com.example.lab.categories;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/categories")
@RequiredArgsConstructor
@Builder
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

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriesDto addCategory(@RequestBody @Validated CategoriesCreationRequest categoriesCreationRequest){
        Categories categories = categoriesService.addCategory(categoriesCreationRequest);
        return new CategoriesDto(categories.getName(), categories.getDescription());
    }

}
