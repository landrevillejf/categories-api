package com.protonmail.landrevillejf.cognos.categories.api.config;

import com.protonmail.landrevillejf.cognos.categories.api.entity.enums.CategoryEnum;
import com.protonmail.landrevillejf.cognos.categories.api.entity.enums.subcategories.*;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;
import com.protonmail.landrevillejf.cognos.categories.api.repository.CategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.repository.SubCategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.util.UUIDGenerator;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.ExecutionTime;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DatabaseInitializer initializes the database with categories and subcategories.
 *
 * @Author(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
 * @Maintainer(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
 * @Revision(
 *     date = "2020-01-01",
 *     revision = 1,
 *     comments = "Author DatabaseInitializer"
 * )
 */
@SuppressWarnings("CheckStyle")
@Component
public class DatabaseInitializer implements CommandLineRunner {
    /**
     * Logger for DatabaseInitializer.
     */
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

    /**
     * Repository for categories.
     */
    private final CategoryRepository categoryRepository;

    /**
     * Repository for subcategories.
     */
    private final SubCategoryRepository subCategoryRepository;

    /**
     * Constructor for DatabaseInitializer.
     *
     * @param categoryRepository    The category repository.
     * @param subCategoryRepository The subcategory repository.
     */
    public DatabaseInitializer(final CategoryRepository categoryRepository, final SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    /**
     * {@inheritDoc}
     */
    @ExecutionTime
    @Override
    public void run(final String... args) {
        initializeCategories();
    }

    /**
     * Initialize categories and subcategories in the database.
     */
    private void initializeCategories() {
        List<CategoryEnum> statusNames = Arrays.asList(
                CategoryEnum.ACADEMICS,
                CategoryEnum.BUSINESS,
                CategoryEnum.DESIGN,
                CategoryEnum.DEVELOPMENT,
                CategoryEnum.FITNESS,
                CategoryEnum.FINANCE_ACCOUNTING,
                CategoryEnum.HEALTH,
                CategoryEnum.IT_SOFTWARE,
                CategoryEnum.LIFESTYLE,
                CategoryEnum.MARKETING,
                CategoryEnum.MUSIC,
                CategoryEnum.OFFICE_PRODUCTIVITY,
                CategoryEnum.PHOTOGRAPHY,
                CategoryEnum.TEACHING,
                CategoryEnum.VIDEO);

        for (CategoryEnum statusName : statusNames) {
            if (!categoryRepository.existsByName(String.valueOf(statusName.getDisplayName()))) {
                Category category = new Category();
                category.setName(String.valueOf(statusName.getDisplayName()));
                category.setDescription(String.valueOf(statusName.getDescription()));
                category.setUid(UUIDGenerator.generateType1UUID().toString());
                category.setCreatedAt(LocalDateTime.now());
                category = categoryRepository.save(category);

                logger.info("Category " + statusName + " inserted into the database.");

                Map<CategoryEnum, List<? extends Enum<?>>> categorySubcategoryMap = new HashMap<>();
                categorySubcategoryMap.put(CategoryEnum.ACADEMICS, Arrays.asList(
                        AcademicsSubCategoriesEnum.Humanities,
                        AcademicsSubCategoriesEnum.Social_Science,
                        AcademicsSubCategoriesEnum.Math_Science
                ));
                // ... (other categorySubcategoryMap entries)

                for (Map.Entry<CategoryEnum, List<? extends Enum<?>>> entry : categorySubcategoryMap.entrySet()) {
                    CategoryEnum categoryEnum = entry.getKey();
                    List<? extends Enum<?>> subCategoriesEnums = entry.getValue();

                    if (categoryEnum == statusName) {
                        for (Enum<?> subCategoryEnum : subCategoriesEnums) {
                            SubCategory subCategory = new SubCategory();
                            subCategory.setName(String.valueOf(subCategoryEnum));
                            subCategory.setDescription("Description for " + subCategoryEnum);
                            subCategory.setUid(UUIDGenerator.generateType1UUID().toString());
                            subCategory.setCreatedAt(LocalDateTime.now());
                            subCategory.setCategory(category);
                            subCategoryRepository.save(subCategory);
                            logger.info("SubCategory " + subCategoryEnum + " inserted into the database.");
                        }
                    }
                }
            } else {
                logger.info("Category " + statusName + " already exists in the database.");
            }
        }
    }
}
