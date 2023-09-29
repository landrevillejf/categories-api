package com.protonmail.landrevillejf.cognos.categories.api.controller;

import com.protonmail.landrevillejf.cognos.categories.api.TestConfig;
import com.protonmail.landrevillejf.cognos.categories.api.controller.CategoryController;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = { TestConfig.class })
public class CategoryControllerTest {

    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testGetAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Academics", "Academics"));
        categories.add(new Category("Business", "Business"));

        // Mock the behavior of your service
        when(categoryService.findAll(anyInt(), anyInt())).thenReturn(categories);

        ResponseEntity<List<Category>> responseEntity = categoryController.getAllCategories(1, 15);
        List<Category> resultCategories = responseEntity.getBody();

        assertNotNull(resultCategories);
        assertEquals(categories.size(), resultCategories.size());
        assertEquals(categories, resultCategories);
    }

    @Test
    void getCategoryByUid() {
    }

    @Test
    void createCategory() {
    }

    @Test
    void updateCategory() {
    }

    @Test
    void deleteCategory() {
    }

    @Test
    void deleteAllCategory() {
    }
}





