package com.protonmail.landrevillejf.cognos.categories.api.config;

import com.protonmail.landrevillejf.cognos.categories.api.entity.enums.CategoryEnum;
import com.protonmail.landrevillejf.cognos.categories.api.entity.enums.subcategories.*;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.repository.CategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.repository.SubCategoryRepository;

import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;
import com.protonmail.landrevillejf.cognos.categories.api.util.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public DatabaseInitializer(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        initializeCategories();
    }

    private void initializeCategories() {
        List<CategoryEnum> statusNames = Arrays.asList(
                CategoryEnum.ACADEMICS,
                CategoryEnum.BUSINESS,
                CategoryEnum.DESIGN,
                CategoryEnum.DEVELOPMENT,
                CategoryEnum.FITNESS,//vide
                CategoryEnum.FINANCE_ACCOUNTING,//vide
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

                // Save the category to get its ID
                category = categoryRepository.save(category);

                logger.info("Category " + statusName + " inserted into the database.");

                // Create a mapping between categories and their subcategories
                Map<CategoryEnum, List<? extends Enum<?>>> categorySubcategoryMap = new HashMap<>();
                categorySubcategoryMap.put(CategoryEnum.ACADEMICS, Arrays.asList(
                        AcademicsSubCategoriesEnum.Humanities,
                        AcademicsSubCategoriesEnum.Social_Science,
                        AcademicsSubCategoriesEnum.Math_Science
                ));
                categorySubcategoryMap.put(CategoryEnum.BUSINESS, Arrays.asList(
                        BusinessSubCategoriesEnum.BUSINESS_LAW,
                        BusinessSubCategoriesEnum.HOME_BUSINESS,
                        BusinessSubCategoriesEnum.MEDIA,
                        BusinessSubCategoriesEnum.DATA_ANALYTICS,
                        BusinessSubCategoriesEnum.COMMUNICATION,
                        BusinessSubCategoriesEnum.ENTREPRENEURSHIP,
                        BusinessSubCategoriesEnum.HUMAN_RESOURCES,
                        BusinessSubCategoriesEnum.INDUSTRY,
                        BusinessSubCategoriesEnum.MANAGEMENT,
                        BusinessSubCategoriesEnum.OPERATIONS,
                        BusinessSubCategoriesEnum.PROJECT_MANAGEMENT,
                        BusinessSubCategoriesEnum.REAL_ESTATES,
                        BusinessSubCategoriesEnum.SALES,
                        BusinessSubCategoriesEnum.STRATEGY
                ));

                categorySubcategoryMap.put(CategoryEnum.DESIGN, Arrays.asList(
                        DesignSubCategoriesEnum.ARCHITECTURAL_DESIGN,
                        DesignSubCategoriesEnum.DESIGN_TOOLS,
                        DesignSubCategoriesEnum.DESIGN_THINKING,
                        DesignSubCategoriesEnum.GAME_DESIGN,
                        DesignSubCategoriesEnum.INTERIOR_DESIGN,
                        DesignSubCategoriesEnum.GRAPHIC_DESIGN,
                        DesignSubCategoriesEnum.ANIMATION,
                        DesignSubCategoriesEnum.FASHION,
                        DesignSubCategoriesEnum.USER_EXPERIENCE,
                        DesignSubCategoriesEnum.WEB_DESIGN,
                        DesignSubCategoriesEnum.OTHER
                ));

                categorySubcategoryMap.put(CategoryEnum.DEVELOPMENT, Arrays.asList(
                        DevelopmentSubCategoriesEnum.DATABASES,
                        DevelopmentSubCategoriesEnum.DEVELOPMENT_TOOLS,
                        DevelopmentSubCategoriesEnum.GAME_DEVELOPMENT,
                        DevelopmentSubCategoriesEnum.ECOMMERCE,
                        DevelopmentSubCategoriesEnum.MOBILE_APPS,
                        DevelopmentSubCategoriesEnum.PROGRAMMING,
                        DevelopmentSubCategoriesEnum.SOFTWARE_ENGINEERING,
                        DevelopmentSubCategoriesEnum.SOFTWARE_TESTING,
                        DevelopmentSubCategoriesEnum.WEB_DEVELOPMENT
                ));



                categorySubcategoryMap.put(CategoryEnum.FINANCE_ACCOUNTING, Arrays.asList(
                        DevelopmentSubCategoriesEnum.DATABASES,
                        DevelopmentSubCategoriesEnum.DEVELOPMENT_TOOLS,
                        DevelopmentSubCategoriesEnum.GAME_DEVELOPMENT,
                        DevelopmentSubCategoriesEnum.ECOMMERCE,
                        DevelopmentSubCategoriesEnum.MOBILE_APPS,
                        DevelopmentSubCategoriesEnum.PROGRAMMING,
                        DevelopmentSubCategoriesEnum.SOFTWARE_ENGINEERING,
                        DevelopmentSubCategoriesEnum.SOFTWARE_TESTING,
                        DevelopmentSubCategoriesEnum.WEB_DEVELOPMENT
                ));

                categorySubcategoryMap.put(CategoryEnum.HEALTH, Arrays.asList(
                        HealthFitnessSubCategoriesEnum.FITNESS,
                        HealthFitnessSubCategoriesEnum.DANCE,
                        HealthFitnessSubCategoriesEnum.GENERAL_HEALTH,
                        HealthFitnessSubCategoriesEnum.MENTAL_HEALTH,
                        HealthFitnessSubCategoriesEnum.YOGA,
                        HealthFitnessSubCategoriesEnum.DIETING,
                        HealthFitnessSubCategoriesEnum.OTHER,
                        HealthFitnessSubCategoriesEnum.SPORTS,
                        HealthFitnessSubCategoriesEnum.MEDITATION,
                        HealthFitnessSubCategoriesEnum.NUTRITION,
                        HealthFitnessSubCategoriesEnum.SAFETY_FIRST_AID,
                        HealthFitnessSubCategoriesEnum.SELF_DEFENSE
                ));

                categorySubcategoryMap.put(CategoryEnum.IT_SOFTWARE, Arrays.asList(
                        ITSoftwareSubCategoriesEnum.HARDWARE,
                        ITSoftwareSubCategoriesEnum.IT_CERTIFICATION,
                        ITSoftwareSubCategoriesEnum.NETWORK_SECURITY,
                        ITSoftwareSubCategoriesEnum.OPERATING_SYSTEMS,
                        ITSoftwareSubCategoriesEnum.OTHER
                ));

                categorySubcategoryMap.put(CategoryEnum.LANGUAGE, Arrays.asList(
                        LanguageSubCategoriesEnum.ARABIC,
                        LanguageSubCategoriesEnum.ITALIAN,
                        LanguageSubCategoriesEnum.CHINESE,
                        LanguageSubCategoriesEnum.ENGLISH,
                        LanguageSubCategoriesEnum.FRENCH,
                        LanguageSubCategoriesEnum.GERMAN,
                        LanguageSubCategoriesEnum.HEBREW,
                        LanguageSubCategoriesEnum.JAPANESE,
                        LanguageSubCategoriesEnum.LATIN,
                        LanguageSubCategoriesEnum.PORTUGUESE,
                        LanguageSubCategoriesEnum.RUSSIAN,
                        LanguageSubCategoriesEnum.SPANISH,
                        LanguageSubCategoriesEnum.OTHER
                ));


                categorySubcategoryMap.put(CategoryEnum.LIFESTYLE, Arrays.asList(
                        LifestyleSubCategoriesEnum.Arts_Crafts,
                        LifestyleSubCategoriesEnum.Beauty_Makeup,
                        LifestyleSubCategoriesEnum.Foods_Beverages,
                        LifestyleSubCategoriesEnum.Gaming,
                        LifestyleSubCategoriesEnum.Home_Improvement,
                        LifestyleSubCategoriesEnum.Pet_Care_Training,
                        LifestyleSubCategoriesEnum.Travel,
                        LifestyleSubCategoriesEnum.OTHER
                ));

                categorySubcategoryMap.put(CategoryEnum.MARKETING, Arrays.asList(
                        MarketingSubCategoriesEnum.Affiliate_Marketing,
                        MarketingSubCategoriesEnum.Content_Marketing,
                        MarketingSubCategoriesEnum.Digital_Marketing,
                        MarketingSubCategoriesEnum.Marketing_Fundamentals,
                        MarketingSubCategoriesEnum.Advertising,
                        MarketingSubCategoriesEnum.Analytics_Automaton,
                        MarketingSubCategoriesEnum.Branding,
                        MarketingSubCategoriesEnum.Growth_Hacking,
                        MarketingSubCategoriesEnum.Non_Digital_Marketing,
                        MarketingSubCategoriesEnum.Product_Marketing,
                        MarketingSubCategoriesEnum.Public_Relations,
                        MarketingSubCategoriesEnum.Search_Engine_Optimization,
                        MarketingSubCategoriesEnum.Social_Media_Marketing,
                        MarketingSubCategoriesEnum.Video_Mobile_Marketing,
                        MarketingSubCategoriesEnum.OTHER
                ));

                categorySubcategoryMap.put(CategoryEnum.MUSIC, Arrays.asList(
                        MusicSubCategoriesEnum.MUSIC_SOFTWARE,
                        MusicSubCategoriesEnum.MUSIC_FUNDAMENTALS,
                        MusicSubCategoriesEnum.MUSIC_TECHNIQUES,
                        MusicSubCategoriesEnum.PRODUCTION,
                        MusicSubCategoriesEnum.VOCALS,
                        MusicSubCategoriesEnum.INSTRUMENTS,
                        MusicSubCategoriesEnum.OTHER
                ));

                categorySubcategoryMap.put(CategoryEnum.OFFICE_PRODUCTIVITY, Arrays.asList(
                        OfficeProductivitySubCategoriesEnum.MICROSOFT,
                        OfficeProductivitySubCategoriesEnum.APPLE,
                        OfficeProductivitySubCategoriesEnum.SAP,
                        OfficeProductivitySubCategoriesEnum.GOOGLE,
                        OfficeProductivitySubCategoriesEnum.INTUIT,
                        OfficeProductivitySubCategoriesEnum.ORACLE,
                        OfficeProductivitySubCategoriesEnum.SALESFORCE,
                        OfficeProductivitySubCategoriesEnum.OTHER
                ));

                categorySubcategoryMap.put(CategoryEnum.PERSONAL_DEVELOPMENT, Arrays.asList(
                        PersonalDevelopmentSubCategoriesEnum.CAREER_DEVELOPMENT,
                        PersonalDevelopmentSubCategoriesEnum.CREATIVITY,
                        PersonalDevelopmentSubCategoriesEnum.PERSONAL_TRANSFORMATION,
                        PersonalDevelopmentSubCategoriesEnum.LEADERSHIP,
                        PersonalDevelopmentSubCategoriesEnum.PERSONAL_FINANCE,
                        PersonalDevelopmentSubCategoriesEnum.HAPPINESS,
                        PersonalDevelopmentSubCategoriesEnum.INFLUENCE,
                        PersonalDevelopmentSubCategoriesEnum.MEMORY_STUDY_SKILLS,
                        PersonalDevelopmentSubCategoriesEnum.MOTIVATION,
                        PersonalDevelopmentSubCategoriesEnum.PARENTING_RELATIONSHIPS,
                        PersonalDevelopmentSubCategoriesEnum.PERSONAL_BRAND_BUILDING,
                        PersonalDevelopmentSubCategoriesEnum.PRODUCTIVITY,
                        PersonalDevelopmentSubCategoriesEnum.STRESS_MANAGEMENT,
                        PersonalDevelopmentSubCategoriesEnum.SELF_ESTEEM,
                        PersonalDevelopmentSubCategoriesEnum.RELIGION_SPIRITUALITY,
                        PersonalDevelopmentSubCategoriesEnum.OTHER
                ));

                categorySubcategoryMap.put(CategoryEnum.PHOTOGRAPHY, Arrays.asList(
                        PhotographySubCategoriesEnum.Commercial_PHOTOGRAPHY,
                        PhotographySubCategoriesEnum.DIGITAL_PHOTOGRAPHY,
                        PhotographySubCategoriesEnum.PORTRAITS,
                        PhotographySubCategoriesEnum.Landscape,
                        PhotographySubCategoriesEnum.Mobile_PHOTOGRAPHY,
                        PhotographySubCategoriesEnum.Black_White,
                        PhotographySubCategoriesEnum.PHOTOGRAPHY_FUNDAMENTALS,
                        PhotographySubCategoriesEnum.PHOTOGRAPHY_TOOLS,
                        PhotographySubCategoriesEnum.Travel_PHOTOGRAPHY,
                        PhotographySubCategoriesEnum.Wedding_PHOTOGRAPHY,
                        PhotographySubCategoriesEnum.Wildlife_PHOTOGRAPHY,
                        PhotographySubCategoriesEnum.OTHER
                ));

                categorySubcategoryMap.put(CategoryEnum.TEACHING, Arrays.asList(
                        TeacherTrainingSubCategories.TEACHING_TOOLS,
                        TeacherTrainingSubCategories.EDUCATIONAL_DEVELOPMENT,
                        TeacherTrainingSubCategories.INSTRUCTIONAL_DESIGN,
                        TeacherTrainingSubCategories.TEACHING_TOOLS,
                        TeacherTrainingSubCategories.OTHER
                ));

                categorySubcategoryMap.put(CategoryEnum.TEST_PREP, Arrays.asList(
                        TestPrepSubCategoriesEnum.GRAD_ENTRY_EXAM,
                        TestPrepSubCategoriesEnum.COLLEGE_ENTRY_EXAM,
                        TestPrepSubCategoriesEnum.INTERNATIONAL_HIGH_SCHOOL,
                        TestPrepSubCategoriesEnum.TEST_TAKING_SKILLS,
                        TestPrepSubCategoriesEnum.OTHER
                ));
                // Add mappings for other categories in a similar manner

                // Iterate through categories and their subcategories to create associations
                for (Map.Entry<CategoryEnum, List<? extends Enum<?>>> entry : categorySubcategoryMap.entrySet()) {
                    CategoryEnum categoryEnum = entry.getKey();
                    List<? extends Enum<?>> subCategoriesEnums = entry.getValue();

                    if (categoryEnum == statusName) {
                        for (Enum<?> subCategoryEnum : subCategoriesEnums) {
                            SubCategory subCategory = new SubCategory();
                            subCategory.setName(String.valueOf(subCategoryEnum));
                            subCategory.setDescription("Description for " + String.valueOf(subCategoryEnum));
                            subCategory.setUid(UUIDGenerator.generateType1UUID().toString());

                            // Set the categoryId for the subcategory
                            subCategory.setCategory(category);

                            // Save the subcategory
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

