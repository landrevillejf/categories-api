package com.protonmail.landrevillejf.cognos.categories.api;

import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.repository.CategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.service.impl.CategoryServiceImpl;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        // Create a list of categories
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Category1", "Description1"));
        categories.add(new Category("Category2", "Description2"));

        // Create a Page object with the list of categories
        Page<Category> categoryPage = new PageImpl<>(categories);

        // Mock the behavior of categoryRepository.findAll to return the Page object
        when(categoryRepository.findAll(any(Pageable.class))).thenReturn(categoryPage);

        // Call the service method
        List<Category> result = categoryService.findAll(1, 15);

        // Assertions
        assertNotNull(result);
        assertEquals(categories.size(), result.size());
        assertEquals(categories, result);
    }

    // Add more service tests for other methods and behaviors
}
