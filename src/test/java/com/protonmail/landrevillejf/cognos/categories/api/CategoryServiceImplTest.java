package com.protonmail.landrevillejf.cognos.categories.api;


import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.repository.CategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<Category> categories = new ArrayList<>();
        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.findAll(1, 15);

        assertNotNull(result);
        assertEquals(categories, result);
    }

    // Add more service tests for other methods and behaviors

}
