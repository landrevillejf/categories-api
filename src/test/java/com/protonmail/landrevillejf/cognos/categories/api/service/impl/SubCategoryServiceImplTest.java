package com.protonmail.landrevillejf.cognos.categories.api.service.impl;

import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;
import com.protonmail.landrevillejf.cognos.categories.api.repository.SubCategoryRepository;
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
import static org.mockito.ArgumentMatchers.any;
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
        List<SubCategory> subCategories = new ArrayList<>();
        subCategories.add(new SubCategory("Category1", "Description1"));
        subCategories.add(new SubCategory("Category2", "Description2"));
        Page<SubCategory> categoryPage = new PageImpl<>(subCategories);
        when(repository.findAll(any(Pageable.class))).thenReturn(categoryPage);
        List<SubCategory> result = service.findAll(1, 15);
        assertNotNull(result);
        assertEquals(subCategories.size(), result.size());
        assertEquals(subCategories, result);
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
