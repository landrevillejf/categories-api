package com.protonmail.landrevillejf.cognos.categories.api;

import com.protonmail.landrevillejf.cognos.categories.api.controller.CategoryController;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.service.common.ICommonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryController.class)
@ContextConfiguration(classes = { TestConfig.class })
public class CategoryIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICommonService<Category> iCommonService;

    @Test
    public void testGetAllCategoriesEndpoint() throws Exception {
        Category category = new Category();
        category.setName("Category Name");
        category.setDescription("Category Description");
        Mockito.when(iCommonService.findAll(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(Collections.singletonList(category));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/cognos-categories-api/categories"))
                .andExpect(status().isOk());
    }
}



