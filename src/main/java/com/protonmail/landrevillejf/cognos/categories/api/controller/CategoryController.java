package com.protonmail.landrevillejf.cognos.categories.api.controller;


import com.protonmail.landrevillejf.cognos.categories.api.config.Api;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.exception.ApiExceptionEnums;
import com.protonmail.landrevillejf.cognos.categories.api.exception.common.CommonApiException;
import com.protonmail.landrevillejf.cognos.categories.api.service.common.ICommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Category", description = "E-Learning Categories management APIs")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(Api.CATEGORY)
@RestController
@RequiredArgsConstructor
public class CategoryController {
    Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private final ICommonService<Category> iCommonService;

    //region Get Category
    @Operation(summary = "Retrieve all Categories", tags = { "categories", "get", "filter" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Category.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There are no Categories", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Category>> getAllCategories (
            @RequestParam(value = "page", defaultValue = "1",required = false) int page,
            @RequestParam(value = "limit", defaultValue = "15" ,required = false) int limit) throws Exception{
        List<Category> categoryList= iCommonService.findAll(page, limit);
        if (categoryList.isEmpty()){
            logger.error(ApiExceptionEnums.EMPTY_LIST.name());
            throw new CommonApiException(ApiExceptionEnums.EMPTY_LIST.name());
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @Operation(summary = "Get a category by its uid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the category",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid uid supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)}) // @formatter:on
    @GetMapping(Api.CATEGORY_BY_CATEGORY_UID)
    public ResponseEntity<Category> getCategoryByUid(@PathVariable("uid") String uid) throws Exception{
        Category category = iCommonService.findByUid(uid);
        if (category !=null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            logger.error(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
            throw new CommonApiException(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
    }
    //endregion

    //region Save Category
    @Operation(summary = "Create a new Category", tags = { "categories", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = Category.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
            )
    public ResponseEntity<Category> createCategory(@RequestBody Category category)throws Exception{
        if(category.getName().isEmpty()){
            logger.error(ApiExceptionEnums.FIELDS_NULL_EXCEPTION.name());
            throw new CommonApiException(ApiExceptionEnums.FIELDS_NULL_EXCEPTION.name());
        }
        Category newCategory= iCommonService.save(category);
        return new ResponseEntity<>(newCategory,HttpStatus.CREATED);
    }
    //endregion

    //region Update Category
    @Operation(summary = "Update a Category by Uid", tags = { "categories", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Category.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping(
            path =Api.CATEGORY_BY_CATEGORY_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Category> updateCategory(@RequestBody Category category,@PathVariable("uid") String uid)throws Exception{
        if(category.getName().isEmpty()){
            logger.error(ApiExceptionEnums.FIELDS_NULL_EXCEPTION.name());
            throw new CommonApiException(ApiExceptionEnums.FIELDS_NULL_EXCEPTION.name());
        }
        if(uid.isEmpty()){
            logger.error(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
            throw new CommonApiException(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        Category newCategory= iCommonService.update(category,uid);
        return new ResponseEntity<>(newCategory,HttpStatus.CREATED);
    }
    //endregion

    //region Delete Category By Uid
    @Operation(summary = "Delete a Category by Uid", tags = { "categories", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping(
            path =Api.CATEGORY_BY_CATEGORY_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Category> deleteCategory(@PathVariable("uid") String uid)throws Exception{
        if(uid.isEmpty()){
            logger.error(ApiExceptionEnums.FIELDS_NULL_EXCEPTION.name());
            throw new CommonApiException(ApiExceptionEnums.FIELDS_NULL_EXCEPTION.name());
        }
        iCommonService.deleteByUid(uid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //endregion

    //region Delete Category All
    @Operation(summary = "Delete all Categories", tags = { "categories", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Category> deleteAllCategory()throws Exception{
        iCommonService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //endregion
}
