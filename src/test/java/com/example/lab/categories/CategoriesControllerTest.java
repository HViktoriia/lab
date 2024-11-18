package com.example.lab.categories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoriesControllerTest {
    @Mock
    private CategoriesService categoriesServiceMock;
    private MockMvc mockMvc;

    @BeforeEach
    void setUpCategoriesControllerTest(){
        CategoriesController controller = new CategoriesController(categoriesServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldReturnAllCategories_whenGettingAllCategories() throws Exception{
        Categories category =
                Categories.builder().build();
        when(categoriesServiceMock.findAllCategories()).thenReturn(List.of(category));
        mockMvc.perform(get("/rest/categories"))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$").isArray())
                .andExpect((ResultMatcher) jsonPath("$[0].name").value(category.getName()))
                .andExpect((ResultMatcher) jsonPath("$[1]").doesNotExist());
    }

    @Test
    void shouldReturnCreatedCategory_whenCreatingCategory() throws Exception{
        CategoriesCreationRequest expectedRequest = new CategoriesCreationRequest(
                "romantic",
                "stories about love");
        Categories categories = new Categories("romantic", "stories about love");
        when(categoriesServiceMock.addCategory(expectedRequest)).thenReturn(categories);
        String requestBody = """ 
                             { 
                               "category": %s, 
                               "name": "%s" 
                               "name":  "romantic"
                               "description": "stories about love"
                             } 
                             """.formatted(  "romantic",
                "stories about love");

        mockMvc.perform(post("/rest/products/add").contentType(MediaType.APPLICATION_JSON
                        )
                        .content(requestBody))
                .andDo(log())
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.name").value(categories.getName()));
    }
    }
