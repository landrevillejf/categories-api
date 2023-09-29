package com.protonmail.landrevillejf.cognos.categories.api.service.impl;

import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.repository.CategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.service.impl.CategoryServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

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
        categories.add(new Category("Category1", "Description1"));
        categories.add(new Category("Category2", "Description2"));
        Page<Category> categoryPage = new PageImpl<>(categories);
        when(categoryRepository.findAll(any(Pageable.class))).thenReturn(categoryPage);
        List<Category> result = categoryService.findAll(1, 15);
        assertNotNull(result);
        assertEquals(categories.size(), result.size());
        assertEquals(categories, result);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findAllByCriteria() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByUid() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void testUpdate() {
    }
}
