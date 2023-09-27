package com.protonmail.landrevillejf.cognos.categories.api.controller;

import com.protonmail.landrevillejf.cognos.categories.api.config.Api;
import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.SubCategoryDto;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;
import com.protonmail.landrevillejf.cognos.categories.api.exception.ApiExceptionEnums;
import com.protonmail.landrevillejf.cognos.categories.api.exception.common.CommonApiException;
import com.protonmail.landrevillejf.cognos.categories.api.service.common.ICommonService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("CheckStyle")
@Tag(name = "SubCategory", description = "E-Learning SubCategories management APIs")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(Api.SUBCATEGORY)
@RestController
@RequiredArgsConstructor
public class SubCategoryController {
    /**
     * Logger
     */
    private final Logger logger = LoggerFactory.getLogger(SubCategoryController.class);
    /**
     * ICommonService
     */
    private final ICommonService<SubCategory> iCommonService;
    /**
     * ModelMapper
     */
    private final ModelMapper modelMapper;
    /**
     * CircuitBreaker
     */
    private final CircuitBreaker circuitBreaker;
    //region Get SubCategory

    /**
     * Retrieve all SubCategory
     * @param page
     * @param limit
     * @return
     * @throws CommonApiException
     */
    @Operation(summary = "Retrieve all SubCategory", tags = { "SubCategory", "get", "filter" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = SubCategory.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There are no SubCategory", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<SubCategory>> getAllCategories (
            @RequestParam(value = "page", defaultValue = "1",required = false) int page,
            @RequestParam(value = "limit", defaultValue = "15" ,required = false) int limit) throws CommonApiException{
        List<SubCategory> categoryList = circuitBreaker.executeSupplier(() -> {
            List<SubCategory> result = iCommonService.findAll(page, limit);
            if (result.isEmpty()){
                logger.error(ApiExceptionEnums.EMPTY_LIST.name());
                throw new CommonApiException(ApiExceptionEnums.EMPTY_LIST.name());
            }
            return result;
        });
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    /**
     * Get a SubCategory by its uid
     * @param uid
     * @return
     * @throws CommonApiException
     */
    @Operation(summary = "Get a SubCategory by its uid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the SubCategory",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SubCategory.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid uid supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "SubCategory not found", content = @Content)}) // @formatter:on
    @GetMapping(Api.SUBCATEGORY_BY_SUBCATEGORY_UID)
    public ResponseEntity<SubCategory> getSubCategoryByUid(@PathVariable("uid") String uid) throws CommonApiException{
        SubCategory category = iCommonService.findByUid(uid);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            logger.error(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
            throw new CommonApiException(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
    }
    //endregion

    //region Save SubCategory

    /**
     * Create a new SubCategory
     * @param dto
     * @return
     * @throws CommonApiException
     */
    @Operation(summary = "Create a new SubCategory", tags = { "SubCategory", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = SubCategory.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
            )
    public ResponseEntity<SubCategory> createSubCategory(@RequestBody SubCategoryDto dto)throws CommonApiException{
        SubCategory category = modelMapper.map(dto, SubCategory.class);
        if (category.getName().isEmpty()){
            logger.error(ApiExceptionEnums.FIELDS_NULL_EXCEPTION.name());
            throw new CommonApiException(ApiExceptionEnums.FIELDS_NULL_EXCEPTION.name());
        }
        SubCategory newCategory= iCommonService.save(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }
    //endregion

    //region Update SubCategory

    /**
     * Update a SubCategory by Uid
     * @param dto
     * @param uid
     * @return
     * @throws CommonApiException
     */
    @Operation(summary = "Update a SubCategory by Uid", tags = { "SubCategory", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = SubCategory.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping(
            path =Api.SUBCATEGORY_BY_SUBCATEGORY_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<SubCategory> updateSubCategory(@RequestBody SubCategoryDto dto,@PathVariable("uid") String uid)throws CommonApiException{
        SubCategory category = modelMapper.map(dto, SubCategory.class);
        if (category.getName().isEmpty()){
            logger.error(ApiExceptionEnums.FIELDS_NULL_EXCEPTION.name());
            throw new CommonApiException(ApiExceptionEnums.FIELDS_NULL_EXCEPTION.name());
        }
        if (uid.isEmpty()){
            logger.error(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
            throw new CommonApiException(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        SubCategory newCategory= iCommonService.update(category, uid);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }
    //endregion

    //region Delete SubCategory By Uid
    /**
     * Delete a SubCategory by Uid
     */
    @Operation(summary = "Delete a SubCategory by Uid", tags = { "SubCategory", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping(
            path =Api.SUBCATEGORY_BY_SUBCATEGORY_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<SubCategory> deleteSubCategory(@PathVariable("uid") String uid)throws CommonApiException{
        if (uid.isEmpty()){
            logger.error(ApiExceptionEnums.FIELDS_NULL_EXCEPTION.name());
            throw new CommonApiException(ApiExceptionEnums.FIELDS_NULL_EXCEPTION.name());
        }
        iCommonService.deleteByUid(uid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //endregion

    /**
     * Delete all SubCategory
     * @return
     * @throws CommonApiException
     */
    //region Delete SubCategory All
    @Operation(summary = "Delete all SubCategory", tags = { "SubCategory", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<SubCategory> deleteAllSubCategory()throws CommonApiException{
        iCommonService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //endregion
}
