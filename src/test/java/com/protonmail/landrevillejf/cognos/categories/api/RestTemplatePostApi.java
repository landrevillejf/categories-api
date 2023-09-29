package com.protonmail.landrevillejf.cognos.categories.api;

import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.util.UUIDGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(classes = CognosCategoriesApiApplication.class,
        webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestTemplatePostApi {

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testAddCategory_withBody_thenSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI("http://localhost:9090/cognos-categories-api/categories/");

        Category category = new Category("Test Name", "Test Description");
        category.setUid(UUIDGenerator.generateType1UUID().toString());

        Category createdCategory = restTemplate.postForObject(uri, category, Category.class);

        Assertions.assertNotNull(createdCategory.getId());
    }

    @Test
    public void testAddCategoryWithoutHeader_success() throws URISyntaxException
    {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = new URI("http://localhost:9090/cognos-categories-api/categories/");
        Category category = new Category("Test Name", "Test Description");
        category.setUid(UUIDGenerator.generateType1UUID().toString());

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.set("X-COM-LOCATION", "USA");

        HttpEntity<Category> httpEntity = new HttpEntity<>(category, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, httpEntity, String.class);

        Assertions.assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    public void testAddCategoryWithoutHeader_fail() throws URISyntaxException
    {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = new URI("http://localhost:9090/cognos-categories-api/categories/");
        Category category = new Category("Test Name", "Test Description");
        category.setUid(UUIDGenerator.generateType1UUID().toString());

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-LOCATION", "USA");
        HttpEntity<Category> request = new HttpEntity<>(category, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        Assertions.assertEquals(400, result.getStatusCodeValue());
    }
}
