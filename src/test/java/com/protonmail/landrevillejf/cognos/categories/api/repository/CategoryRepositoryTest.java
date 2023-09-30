package com.protonmail.landrevillejf.cognos.categories.api.repository;

import com.protonmail.landrevillejf.cognos.categories.api.CognosCategoriesApiApplication;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;
import com.protonmail.landrevillejf.cognos.categories.api.util.UUIDGenerator;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringJUnitConfig(classes = CognosCategoriesApiApplication.class)
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void findByName() {
        // Create a sample category
        Category category = new Category();
        category.setName("TestCategory");
        categoryRepository.save(category);

        // Try to find the category by name
        Category foundCategory = categoryRepository.findByName("TestCategory");

        // Assert that the category is found
        assertNotNull(foundCategory);
        assertEquals("TestCategory", foundCategory.getName());
    }

    @Test
    void findByNameContaining() {
        // Create sample categories
        Category category1 = new Category();
        category1.setName("TestCategory1");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setName("AnotherCategory");
        categoryRepository.save(category2);

        // Try to find categories containing "Category"
        Page<Category> categoriesContainingCategory = categoryRepository.findByNameContaining(
                PageRequest.of(0, 10), "Category");

        // Assert that both categories are found
        assertNotNull(categoriesContainingCategory);
        assertEquals(2, categoriesContainingCategory.getTotalElements());
    }

    @Test
    @Transactional
    void existsByName() {
        // Create a sample category
        Category category = new Category();
        category.setName("SampleCategory");
        category.setUid(UUIDGenerator.generateType1UUID().toString());

        categoryRepository.save(category);

        // Check if the category exists by name
        boolean exists = categoryRepository.existsByName("SampleCategory");

        // Assert that the category exists
        assertTrue(exists);
    }
}
