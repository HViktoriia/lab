package com.example.lab.product;

import com.example.lab.categories.Categories;
import com.example.lab.categories.CategoriesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvcBuilder;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private ProductService productServiceMock;
    private MockMvc mockMvc;

    @BeforeEach
    void setUpProductControllerTest(){
        ProductController controller = new ProductController(productServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void  shouldReturnAllProducts_whenGettingAllProducts() throws Exception {
        Categories category =
                Categories.builder().categoriesId(1L).description("deck").name("fantasy").build();
            Product product =
                    Product.builder().price(20D).title("Harry Potter").bookCategory("fantasy").build();
        when(productServiceMock.findAllProducts()).thenReturn(List.of(product));
        mockMvc.perform(get("/rest/products"))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$").isArray())
                .andExpect((ResultMatcher) jsonPath("$[0].title").value(product.getTitle()))
                .andExpect((ResultMatcher) jsonPath("$[1]").doesNotExist());
    }
    @Test
    void shouldReturnCreatedProduct_whenCreatingProduct() throws Exception {
        ProductCreationRequest expectedRequest = new ProductCreationRequest(
                "Megaland",
                "Maleny",
                "fantasy",
                "nice book",
                20.00);
        Product product = new Product("Megaland", "Maleny", "fantasy", "nice book", 20.00);

        when(productServiceMock.addProduct(expectedRequest)).thenReturn(product);
        String requestBody = """ 
                             { 
                               "category": %s, 
                               "name": "%s" 
                               "title":  "Megaland"
                               "author": "Maleny"
                               "categoryName": "fantasy"
                               "description": "nice book"
                               "price": 20.00
                             } 
                             """.formatted("Megaland",
                "Maleny",
                "fantasy",
                "nice book",
                20.00);


        mockMvc.perform(post("/rest/products/add").contentType(MediaType.APPLICATION_JSON
                        )
                        .content(requestBody))
                .andDo(log())
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.name").value(product.getTitle()));
    }

    @Test
    void updateProduct_ShouldReturnUpdatedProduct() throws Exception {
        Long productId = 1L;
        ProductUpdateRequest updateRequest = new ProductUpdateRequest(
                "Updated Title",
                "Updated Author",
                "fantasy",
                "Updated Description",
                199.99
        );
        Product updatedProduct = new Product(
                "Updated Title",
                "Updated Author",
                "Updated Category",
                "Updated Description",
                199.99
        );

        // Mock the service behavior
        when(productServiceMock.updateProduct(eq(productId), any(ProductUpdateRequest.class))).thenReturn(updatedProduct);
        String updateRequestTest = ("""
                                {
                                    "category": %s, 
                                    "name": "%s" 
                                    "title": "Updated Title",
                                    "author": "Updated Author",
                                    "categoryId": 2,
                                    "description": "Updated Description",
                                    "price": 199.99
                                }
                                """);
        mockMvc.perform((RequestBuilder) patch("/rest/products/{id}", productId).contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.valueOf(updateRequestTest)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.title", is(updatedProduct.getTitle())))
                .andExpect((ResultMatcher) jsonPath("$.author", is(updatedProduct.getAuthor())))
                .andExpect((ResultMatcher) jsonPath("$.bookCategory", is(updatedProduct.getBookCategory())))
                .andExpect((ResultMatcher) jsonPath("$.bookDescription", is(updatedProduct.getBook_description())))
                .andExpect((ResultMatcher) jsonPath("$.price", is(updatedProduct.getPrice())));
    }
}
