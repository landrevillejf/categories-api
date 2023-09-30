package com.protonmail.landrevillejf.cognos.categories.api.repository;

import com.protonmail.landrevillejf.cognos.categories.api.CognosCategoriesApiApplication;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;
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
class SubCategoryRepositoryTest {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void findByName() {
        // Create a sample subcategory and save it to the database
        SubCategory subCategory = new SubCategory();
        subCategory.setName("SampleSubCategory");
        subCategoryRepository.save(subCategory);

        // Perform the findByName operation
        SubCategory foundSubCategory = subCategoryRepository.findByName("SampleSubCategory");

        // Assert that the found subcategory matches the expected one
        assertNotNull(foundSubCategory);
        assertEquals("SampleSubCategory", foundSubCategory.getName());
    }

    @Test
    void findByNameContaining() {
        // Create and save sample subcategories with names containing "Sample"
        SubCategory subCategory1 = new SubCategory();
        subCategory1.setName("SampleSubCategory1");
        subCategoryRepository.save(subCategory1);

        SubCategory subCategory2 = new SubCategory();
        subCategory2.setName("SubCategorySample2");
        subCategoryRepository.save(subCategory2);

        // Perform the findByNameContaining operation
        Page<SubCategory> subCategories = subCategoryRepository.findByNameContaining(PageRequest.of(0, 10), "Sample");

        // Assert that both subcategories are found
        assertEquals(2, subCategories.getTotalElements());
    }

    @Test
    void existsByName() {
        // Create a sample subcategory and save it to the database
        SubCategory subCategory = new SubCategory();
        subCategory.setName("SampleSubCategory");
        subCategoryRepository.save(subCategory);

        // Check if the subcategory exists by name
        boolean exists = subCategoryRepository.existsByName("SampleSubCategory");

        // Assert that the subcategory exists
        assertTrue(exists);
    }

    @Test
    void countByCategory() {
        // Create a sample category and subcategories associated with it
        Category category = new Category();
        category.setName("SampleCategory");
        SubCategory subCategory1 = new SubCategory();
        subCategory1.setName("SubCategory1");
        subCategory1.setCategory(category);
        SubCategory subCategory2 = new SubCategory();
        subCategory2.setName("SubCategory2");
        subCategory2.setCategory(category);

        // Save the category and subcategories to the database
        category = categoryRepository.save(category);
        subCategoryRepository.save(subCategory1);
        subCategoryRepository.save(subCategory2);

        // Perform the countByCategory operation
        int count = subCategoryRepository.countByCategory(category);

        // Assert that the count matches the number of associated subcategories
        assertEquals(2, count);
    }
}
