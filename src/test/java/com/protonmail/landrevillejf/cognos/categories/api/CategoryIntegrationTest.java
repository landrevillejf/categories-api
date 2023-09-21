package com.protonmail.landrevillejf.cognos.categories.api;


import com.protonmail.landrevillejf.cognos.categories.api.controller.CategoryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryController categoryController;

    @Test
    public void testGetAllCategoriesEndpoint() throws Exception {
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk());
    }

    // Add more integration tests as needed for different endpoints and behaviors

}
