package com.protonmail.landrevillejf.cognos.categories.api.service.impl;

import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;

import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;
import com.protonmail.landrevillejf.cognos.categories.api.exception.common.CommonApiException;
import com.protonmail.landrevillejf.cognos.categories.api.repository.CategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.repository.SubCategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.util.UUIDGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl service;

    @Mock
    private CategoryRepository repository;

    @Mock
    private SubCategoryRepository subCategoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Category1", "Description1"));
        categories.add(new Category("Category2", "Description2"));
        Page<Category> categoryPage = new PageImpl<>(categories);
        when(repository.findAll(any(Pageable.class))).thenReturn(categoryPage);
        List<Category> result = service.findAll(1, 15);
        assertNotNull(result);
        assertEquals(categories.size(), result.size());
        assertEquals(categories, result);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        // Arrange
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Category1", "Description1"));
        categories.add(new Category("Category2", "Description2"));
        Page<Category> categoryPage = new PageImpl<>(categories);
        when(repository.findAll(any(Pageable.class))).thenReturn(categoryPage);

        // Act
        List<Category> result = service.findAll(1, 15);

        // Assert
        assertNotNull(result);
        assertEquals(categories.size(), result.size());
        assertEquals(categories, result);
    }

    @Test
    void findAllByCriteria() {
        // Arrange
        String searchCriteria = "Category1";
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Category1", "Description1"));
        categories.add(new Category("Category1_2", "Description1_2"));
        Page<Category> categoryPage = new PageImpl<>(categories);

        // Mock the repository to return the list of categories based on the criteria
        when(repository.findByNameContaining(any(Pageable.class), eq(searchCriteria))).thenReturn(categoryPage);

        // Act
        List<Category> result = service.findAllByCriteria(1, 15, searchCriteria);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size()); // Assuming two categories match the criteria
        assertEquals(categories, result);
    }


    @Test
    void findById() {
        // Arrange
        long id = 1;
        Category category = new Category("Category1", "Description1");
        when(repository.findById(id)).thenReturn(Optional.of(category));

        // Act
        Category result = service.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(category, result);
    }

    @Test
    void findById_NotFound() {
        // Arrange
        long id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act
        Category result = service.findById(id);

        // Assert
        assertNull(result);
    }

    @Test
    void findByUid() {
        // Arrange
        String uid = "01ee5e28-a952-1cb1-b723-7f650bcdd7ad";
        Category category = new Category("Category1", "Description1");
        when(repository.findByUid(uid)).thenReturn(category);

        // Act
        Category result = service.findByUid(uid);

        // Assert
        assertNotNull(result);
        assertEquals(category, result);
    }

    @Test
    void findByUid_NotFound() {
        // Arrange
        String uid = UUIDGenerator.generateType1UUID().toString();
        when(repository.findByUid(uid)).thenReturn(null);

        // Act
        Category result = service.findByUid(uid);

        // Assert
        assertNull(result);
    }

    @Test
    void save() {
        // Arrange
        Category category = new Category("Category1", "Description1");
        when(repository.save(category)).thenReturn(category);

        // Act
        Category result = service.save(category);

        // Assert
        assertNotNull(result);
        assertEquals(category, result);
    }

    @Test
    void update() {
        // Arrange
        String uid = "01ee5e28-a952-1cb1-b723-7f650bcdd7ad";
        Category existingCategory = new Category("Academics", "Academics");
        when(repository.findByUid(uid)).thenReturn(existingCategory);

        Category updatedCategory = new Category("UpdatedCategory", "UpdatedDescription");
        when(repository.save(updatedCategory)).thenReturn(updatedCategory);

        // Act
        Category result = service.update(updatedCategory, uid);

        // Assert
        assertNotNull(result);
        assertEquals(updatedCategory, result);
    }



    @Test
    void update_NotFound() {
        // Arrange
        String uid = UUIDGenerator.generateType1UUID().toString();
        when(repository.findByUid(uid)).thenReturn(null);

        Category updatedCategory = new Category("UpdatedCategory", "UpdatedDescription");

        // Act and Assert
        assertThrows(CommonApiException.class, () -> service.update(updatedCategory, uid));
    }

    @Test
    void delete() {
        // Arrange
        Category category = new Category("Category1", "Description1");
        category.setUid(UUIDGenerator.generateType1UUID().toString());

        // Create a list of sample subcategories associated with 'category'
        List<SubCategory> subCategories = new ArrayList<>();
        SubCategory subCategory1 = new SubCategory();
        subCategory1.setName("SubCategory1");
        subCategory1.setUid(UUIDGenerator.generateType1UUID().toString());
        subCategory1.setCategory(category);
        subCategories.add(subCategory1);

        SubCategory subCategory2 = new SubCategory();
        subCategory2.setName("SubCategory2");
        subCategory2.setUid(UUIDGenerator.generateType1UUID().toString());
        subCategory2.setCategory(category);
        subCategories.add(subCategory2);

        category.setSubCategories(subCategories);

        // Mock the repository to return the specified category when findByUid is called
        when(repository.findByUid(category.getUid())).thenReturn(category);

        // Act
        assertDoesNotThrow(() -> service.delete(category));

        // Assert: Verify that the repository's delete method was called with the specified category
        verify(repository, times(1)).delete(eq(category));
        // Assert: Verify that the delete method was called for each associated subcategory
        for (SubCategory subCategory : subCategories) {
            verify(subCategoryRepository, times(1)).delete(eq(subCategory));
        }
    }

    @Test
    void deleteCategoryWithSubcategories() {
        // Create a sample category and associated subcategories
        Category category = new Category();
        category.setUid("category-uid");
        category.setName("SampleCategory");
        List<SubCategory> subCategories = new ArrayList<>();
        SubCategory subCategory1 = new SubCategory();
        subCategory1.setUid("sub-category-1-uid");
        subCategory1.setName("SubCategory1");
        subCategory1.setCategory(category);
        subCategories.add(subCategory1);
        category.setSubCategories(subCategories);

        // Mock the repository to return the category when findByUid is called
        when(repository.findByUid(category.getUid())).thenReturn(category);

        // Call the delete method
        service.delete(category);

        // Verify that deleteAll was called on the subCategoryRepository with the associated subcategories
        verify(subCategoryRepository, times(1)).deleteAll(subCategories);
        // Verify that delete was called on the categoryRepository with the category
        verify(repository, times(1)).delete(category);
    }

    @Test
    void deleteCategoryWithoutSubcategories() {
        // Create a sample category without associated subcategories
        Category category = new Category();
        category.setUid("category-uid");
        category.setName("SampleCategory");

        // Mock the repository to return the category when findByUid is called
        when(repository.findByUid(category.getUid())).thenReturn(category);

        // Call the delete method
        service.delete(category);

        // Verify that deleteAll was not called on the subCategoryRepository
        verify(subCategoryRepository, never()).deleteAll(any());
        // Verify that delete was called on the categoryRepository with the category
        verify(repository, times(1)).delete(category);
    }

    @Test
    void deleteByUid() {
        // Arrange
        String uid = "01ee5e28-a952-1cb1-b723-7f650bcdd7ad";
        Category category = new Category("Category1", "Description1");
        when(repository.findByUid(uid)).thenReturn(category);

        // Act
        assertDoesNotThrow(() -> service.deleteByUid(uid));

        // Assert
        verify(repository, times(1)).delete(eq(category));
    }


    @Test
    void deleteByUid_NotFound() {
        // Arrange
        String uid = UUIDGenerator.generateType1UUID().toString();
        when(repository.findByUid(uid)).thenReturn(null);

        // Act and Assert
        assertThrows(CommonApiException.class, () -> service.deleteByUid(uid));
    }

    @Test
    void deleteAll() {
        // Arrange: Mock the repository to return a list of sub-categories
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Category1", "Description1"));
        when(repository.findAll()).thenReturn(categories);

        // Act
        assertDoesNotThrow(() -> service.deleteAll());

        // Assert: Verify that the repository's deleteAll method was called
        verify(repository, times(1)).deleteAll();
    }


    @Test
    void count() {
        // Arrange
        long expectedCount = 42;
        when(repository.count()).thenReturn(expectedCount);

        // Act
        long result = service.count();

        // Assert
        assertEquals(expectedCount, result);
    }
}
