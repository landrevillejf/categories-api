package com.protonmail.landrevillejf.cognos.categories.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.protonmail.landrevillejf.cognos.categories.api.controller.CategoryController;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.service.common.ICommonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(CategoryControllerTest.class)
@ContextConfiguration(classes = { TestConfig.class })
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICommonService<Category> categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCategories() throws Exception {
        Category category1 = new Category("Category1", "Description1");
        Category category2 = new Category("Category2", "Description2");
        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryService.findAll(1, 15)).thenReturn(categories);

        mockMvc.perform(MockMvcRequestBuilders.get("/cognos-categories-api/categories")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(categories)));
    }
}
