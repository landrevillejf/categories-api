package com.protonmail.landrevillejf.cognos.categories.api.controller;

import com.protonmail.landrevillejf.cognos.categories.api.TestConfig;
import com.protonmail.landrevillejf.cognos.categories.api.controller.CategoryController;
import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.CategoryDto;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.service.impl.CategoryServiceImpl;
import com.protonmail.landrevillejf.cognos.categories.api.util.EntityDtoMapper;
import com.protonmail.landrevillejf.cognos.categories.api.util.UUIDGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
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
        String categoryUId = "01ee5e28-a952-1cb1-b723-7f650bcdd7ad";
        Category category = new Category("Academics", "Academics");
        category.setUid(categoryUId);

        when(categoryService.findByUid(categoryUId)).thenReturn(category);

        ResponseEntity<Category> responseEntity = categoryController.getCategoryByUid(categoryUId);
        Category resultCategory = responseEntity.getBody();

        assertNotNull(resultCategory);
        assertEquals(categoryUId, resultCategory.getUid());
        assertEquals(category.getName(), resultCategory.getName());
        assertEquals(category.getDescription(), resultCategory.getDescription());
    }

    @Test
    void getCategoryByUidNotFound() {
        String categoryUId = UUIDGenerator.generateType1UUID().toString();

        when(categoryService.findByUid(categoryUId)).thenThrow(new Exception("NOT FOUND"));

        ResponseEntity<Category> responseEntity = categoryController.getCategoryByUid(categoryUId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void createCategory() {
        Category categoryToCreate = new Category();
        categoryToCreate.setName("test");
        categoryToCreate.setDescription("test");

        when(categoryService.save(categoryToCreate)).thenReturn(categoryToCreate);

        CategoryDto categoryToCreateDto = EntityDtoMapper.convertToDto(categoryToCreate, CategoryDto.class);

        ResponseEntity<Category> responseEntity = categoryController.createCategory(categoryToCreateDto);
        Category createdCategory = responseEntity.getBody();

        assertNotNull(createdCategory);
        assertEquals(categoryToCreate.getName(), createdCategory.getName());
        assertEquals(categoryToCreate.getDescription(), createdCategory.getDescription());
    }

    @Test
    void updateCategory() {
        String categoryUId = "01ee5e28-a952-1cb1-b723-7f650bcdd7ad";
        Category existingCategory = new Category("Academics", "Academics");
        existingCategory.setUid(categoryUId);

        Category updatedCategory = new Category("Business", "Business");
        updatedCategory.setUid(categoryUId);

        when(categoryService.findByUid(categoryUId)).thenReturn(existingCategory);
        when(categoryService.update(updatedCategory,categoryUId)).thenReturn(updatedCategory);

        CategoryDto updatedCategoryDto = EntityDtoMapper.convertToDto(updatedCategory, CategoryDto.class);

        ResponseEntity<Category> responseEntity = categoryController.updateCategory(updatedCategoryDto,categoryUId);
        Category resultCategory = responseEntity.getBody();

        assertNotNull(resultCategory);
        assertEquals(categoryUId, resultCategory.getUid());
        assertEquals(updatedCategory.getName(), resultCategory.getName());
        assertEquals(updatedCategory.getDescription(), resultCategory.getDescription());
    }

    @Test
    void updateCategoryNotFound() {
        String categoryUId = UUIDGenerator.generateType1UUID().toString();
        Category updatedCategory = new Category("Business", "Business");
        updatedCategory.setUid(categoryUId);

        when(categoryService.findByUid(categoryUId)).thenThrow(new Exception("NOT FOUND"));

        CategoryDto updatedCategoryDto = EntityDtoMapper.convertToDto(updatedCategory, CategoryDto.class);

        ResponseEntity<Category> responseEntity = categoryController.updateCategory(updatedCategoryDto,categoryUId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void deleteCategory() {
        String categoryUId = "01ee5e28-a952-1cb1-b723-7f650bcdd7ad";
        Category categoryToDelete = new Category("Academics", "Academics");
        categoryToDelete.setUid(categoryUId);

        when(categoryService.findByUid(categoryUId)).thenReturn(categoryToDelete);

        ResponseEntity<Category> responseEntity = categoryController.deleteCategory(categoryUId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void deleteCategoryNotFound() {
        String categoryUId = UUIDGenerator.generateType1UUID().toString();

        when(categoryService.findByUid(categoryUId)).thenThrow(new Exception("NOT FOUND"));

        ResponseEntity<Category> responseEntity = categoryController.deleteCategory(categoryUId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void deleteAllCategory() {

        ResponseEntity<Category> responseEntity = categoryController.deleteAllCategory();

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}





