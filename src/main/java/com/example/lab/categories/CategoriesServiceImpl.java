package com.example.lab.categories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;

    @Override
    public List<Categories> findAllCategories(){
        return categoriesRepository.findAll();
    }

    @Override
    public Categories addCategory(CategoriesCreationRequest categoriesCreationRequest){
        Categories category = new Categories();
        category.setName(categoriesCreationRequest.getName());
        category.setDescription(categoriesCreationRequest.getDescription());
        return categoriesRepository.save(category);
    }
}
