package com.protonmail.landrevillejf.cognos.categories.api.service.impl;

import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;
import com.protonmail.landrevillejf.cognos.categories.api.exception.common.CommonApiException;
import com.protonmail.landrevillejf.cognos.categories.api.repository.SubCategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.util.UUIDGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SubCategoryServiceImplTest {

    @InjectMocks
    private SubCategoryServiceImpl service;

    @Mock
    private SubCategoryRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        // Arrange
        List<SubCategory> subCategories = new ArrayList<>();
        subCategories.add(new SubCategory("Category1", "Description1"));
        subCategories.add(new SubCategory("Category2", "Description2"));
        Page<SubCategory> categoryPage = new PageImpl<>(subCategories);
        when(repository.findAll(any(Pageable.class))).thenReturn(categoryPage);

        // Act
        List<SubCategory> result = service.findAll(1, 15);

        // Assert
        assertNotNull(result);
        assertEquals(subCategories.size(), result.size());
        assertEquals(subCategories, result);
    }

    @Test
    void findAllByCriteria() {
        // Arrange
        String searchCriteria = "Category1"; // Replace with your desired search criteria
        List<SubCategory> subCategories = new ArrayList<>();
        subCategories.add(new SubCategory("Category1", "Description1"));
        subCategories.add(new SubCategory("Category1_2", "Description1_2")); // Additional sub-category with the same search criteria
        Page<SubCategory> categoryPage = new PageImpl<>(subCategories);

        // Mock the repository to return the list of sub-categories based on the criteria
        when(repository.findByNameContaining(any(Pageable.class), eq(searchCriteria))).thenReturn(categoryPage);

        // Act
        List<SubCategory> result = service.findAllByCriteria(1, 15, searchCriteria);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size()); // Assuming two sub-categories match the criteria
        assertEquals(subCategories, result);
    }


    @Test
    void findById() {
        // Arrange
        long id = 1;
        SubCategory subCategory = new SubCategory("Category1", "Description1");
        when(repository.findById(id)).thenReturn(Optional.of(subCategory));

        // Act
        SubCategory result = service.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(subCategory, result);
    }

    @Test
    void findById_NotFound() {
        // Arrange
        long id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act
        SubCategory result = service.findById(id);

        // Assert
        assertNull(result);
    }

    @Test
    void findByUid() {
        // Arrange
        String uid = "01ee5e28-a952-1cb1-b723-7f650bcdd7ad";
        SubCategory subCategory = new SubCategory("Category1", "Description1");
        when(repository.findByUid(uid)).thenReturn(subCategory);

        // Act
        SubCategory result = service.findByUid(uid);

        // Assert
        assertNotNull(result);
        assertEquals(subCategory, result);
    }

    @Test
    void findByUid_NotFound() {
        // Arrange
        String uid = UUIDGenerator.generateType1UUID().toString();
        when(repository.findByUid(uid)).thenReturn(null);

        // Act
        SubCategory result = service.findByUid(uid);

        // Assert
        assertNull(result);
    }

    @Test
    void save() {
        // Arrange
        SubCategory subCategory = new SubCategory("Category1", "Description1");
        when(repository.save(subCategory)).thenReturn(subCategory);

        // Act
        SubCategory result = service.save(subCategory);

        // Assert
        assertNotNull(result);
        assertEquals(subCategory, result);
    }

    @Test
    void update() {
        // Arrange
        String uid = "01ee5e28-a952-1cb1-b723-7f650bcdd7ad";
        SubCategory existingSubCategory = new SubCategory("Category1", "Description1");
        when(repository.findByUid(uid)).thenReturn(existingSubCategory);

        ArgumentCaptor<SubCategory> subCategoryCaptor = ArgumentCaptor.forClass(SubCategory.class);
        when(repository.save(subCategoryCaptor.capture())).thenAnswer(invocation -> {
            SubCategory savedSubCategory = subCategoryCaptor.getValue();

            // Update existingSubCategory with values from savedSubCategory
            existingSubCategory.setName(savedSubCategory.getName());
            existingSubCategory.setDescription(savedSubCategory.getDescription());
            existingSubCategory.setUpdatedAt(savedSubCategory.getUpdatedAt());

            return existingSubCategory;
        });

        SubCategory updatedSubCategory = new SubCategory("UpdatedCategory", "UpdatedDescription");

        // Act
        SubCategory result = service.update(updatedSubCategory, uid);

        // Assert
        assertNotNull(result);
        assertEquals("UpdatedCategory", result.getName());
        assertEquals("UpdatedDescription", result.getDescription());
    }





    @Test
    void update_NotFound() {
        // Arrange
        String uid = UUIDGenerator.generateType1UUID().toString();
        when(repository.findByUid(uid)).thenReturn(null);

        SubCategory updatedSubCategory = new SubCategory("UpdatedCategory", "UpdatedDescription");

        // Act and Assert
        assertThrows(CommonApiException.class, () -> service.update(updatedSubCategory, uid));
    }

    @Test
    void delete() {
        // Arrange
        SubCategory subCategory = new SubCategory("Category1", "Description1");

        // Mock the repository to return the specified subCategory when findByUid is called
        when(repository.findByUid(subCategory.getUid())).thenReturn(subCategory);

        // Act
        assertDoesNotThrow(() -> service.delete(subCategory));

        // Assert: Verify that the repository's delete method was called with the specified subCategory
        verify(repository, times(1)).delete(eq(subCategory));
    }

    @Test
    void deleteByUid() {
        // Arrange
        String uid = "01ee5e28-a952-1cb1-b723-7f650bcdd7ad";
        SubCategory subCategory = new SubCategory("Category1", "Description1");
        when(repository.findByUid(uid)).thenReturn(subCategory);

        // Act
        assertDoesNotThrow(() -> service.deleteByUid(uid));

        // Assert
        verify(repository, times(1)).delete(eq(subCategory));
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
        List<SubCategory> subCategories = new ArrayList<>();
        subCategories.add(new SubCategory("Category1", "Description1"));
        when(repository.findAll()).thenReturn(subCategories);

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

