package com.protonmail.landrevillejf.cognos.categories.api;


import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
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

    @Test
    public void testConstructorWithAllParameters() {
        // If you have additional fields in your class, you can test them here
        // Example: Category category = new Category("Test Name", "Test Description", subCategories);
        // assertEquals("Test Name", category.getName());
        // assertEquals("Test Description", category.getDescription());
        // assertEquals(subCategories, category.getSubCategories());
    }

    // Add more test cases as needed for other methods or behavior

}

