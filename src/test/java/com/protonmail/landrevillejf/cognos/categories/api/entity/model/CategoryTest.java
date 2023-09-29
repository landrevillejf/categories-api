package com.protonmail.landrevillejf.cognos.categories.api.entity.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
    }

    @Test
    void getDescription() {
    }

    @Test
    void getSubCategories() {
    }

    @Test
    void getCreatedAt() {
    }

    @Test
    void getUpdatedAt() {
    }

    @Test
    void setName() {
    }

    @Test
    void setDescription() {
    }

    @Test
    void setSubCategories() {
    }

    @Test
    void setCreatedAt() {
    }

    @Test
    void setUpdatedAt() {
    }

    @Test
    public void testGetName() {
        category.setName("Test Category");
        assertEquals("Test Category", category.getName());
    }

    @Test
    public void testGetDescription() {
        category.setDescription("Test Description");
        assertEquals("Test Description", category.getDescription());
    }

    @Test
    public void testGetSubCategories() {
        // You can set up sub-categories and test the getter here
        // Example: category.setSubCategories(subCategories);
        // assertEquals(subCategories, category.getSubCategories());
    }

    @Test
    public void testConstructorWithParameters() {
        Category category = new Category("Test Name", "Test Description");
        assertEquals("Test Name", category.getName());
        assertEquals("Test Description", category.getDescription());
    }
}
