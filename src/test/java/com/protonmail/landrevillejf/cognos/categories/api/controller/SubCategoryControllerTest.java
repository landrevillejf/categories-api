package com.protonmail.landrevillejf.cognos.categories.api.controller;

import com.protonmail.landrevillejf.cognos.categories.api.TestConfig;
import com.protonmail.landrevillejf.cognos.categories.api.service.impl.SubCategoryServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.protonmail.landrevillejf.cognos.categories.api.controller.SubCategoryController;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SubCategoryControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private SubCategoryController subCategoryController;

    @Mock
    private SubCategoryServiceImpl subCategoryService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(subCategoryController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllSubCategories() throws Exception {
        List<SubCategory> subCategories = new ArrayList<>();
        SubCategory subCategory1 = new SubCategory();
        subCategory1.setUid("sub-category-1-uid");
        subCategory1.setName("SubCategory1");
        SubCategory subCategory2 = new SubCategory();
        subCategory2.setUid("sub-category-2-uid");
        subCategory2.setName("SubCategory2");
        subCategories.add(subCategory1);
        subCategories.add(subCategory2);

        when(subCategoryService.findAll(1,15)).thenReturn(subCategories);

        mockMvc.perform(get("/api/subcategories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(subCategories.size()))
                .andExpect(jsonPath("$[0].uid").value(subCategory1.getUid()))
                .andExpect(jsonPath("$[0].name").value(subCategory1.getName()))
                .andExpect(jsonPath("$[1].uid").value(subCategory2.getUid()))
                .andExpect(jsonPath("$[1].name").value(subCategory2.getName()));

        verify(subCategoryService, times(1)).findAll(1,15);
    }

    @Test
    void getSubCategoryByUid() throws Exception {
        SubCategory subCategory = new SubCategory();
        subCategory.setUid("sub-category-uid");
        subCategory.setName("SampleSubCategory");

        when(subCategoryService.findByUid(subCategory.getUid())).thenReturn(subCategory);

        mockMvc.perform(get("/api/subcategories/{uid}", subCategory.getUid()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.uid").value(subCategory.getUid()))
                .andExpect(jsonPath("$.name").value(subCategory.getName()));

        verify(subCategoryService, times(1)).findByUid(subCategory.getUid());
    }

    @Test
    void createSubCategory() throws Exception {
        SubCategory subCategory = new SubCategory();
        subCategory.setName("SampleSubCategory");

        when(subCategoryService.save(any(SubCategory.class))).thenReturn(subCategory);

        mockMvc.perform(post("/api/subcategories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subCategory)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.uid").exists())
                .andExpect(jsonPath("$.name").value(subCategory.getName()));

        verify(subCategoryService, times(1)).save(any(SubCategory.class));
    }

    @Test
    void updateSubCategory() throws Exception {
        SubCategory subCategory = new SubCategory();
        subCategory.setUid("sub-category-uid");
        subCategory.setName("SampleSubCategory");

        when(subCategoryService.update( any(SubCategory.class)))
                .thenReturn(subCategory);

        mockMvc.perform(put("/api/subcategories/{uid}", subCategory.getUid())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subCategory)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.uid").value(subCategory.getUid()))
                .andExpect(jsonPath("$.name").value(subCategory.getName()));

        verify(subCategoryService, times(1)).update(eq(any(SubCategory.class)));
    }

    @Test
    void deleteSubCategory() throws Exception {
        String subCategoryUid = "sub-category-uid";

        mockMvc.perform(delete("/api/subcategories/{uid}", subCategoryUid))
                .andExpect(status().isNoContent());

        verify(subCategoryService, times(1)).deleteByUid(subCategoryUid);
    }

    @Test
    void deleteAllSubCategories() throws Exception {
        mockMvc.perform(delete("/api/subcategories"))
                .andExpect(status().isNoContent());

        verify(subCategoryService, times(1)).deleteAll();
    }
}

