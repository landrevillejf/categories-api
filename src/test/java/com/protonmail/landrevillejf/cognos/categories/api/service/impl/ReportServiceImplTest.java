package com.protonmail.landrevillejf.cognos.categories.api.service.impl;

import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;
import com.protonmail.landrevillejf.cognos.categories.api.util.UUIDGenerator;
import com.protonmail.landrevillejf.cognos.categories.api.util.Utils;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.FileDTO;
import com.protonmail.landrevillejf.cognos.categories.api.repository.CategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.repository.SubCategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.util.jasperreport.simple.SimpleReportExporter;
import com.protonmail.landrevillejf.cognos.categories.api.util.jasperreport.simple.SimpleReportFiller;
import net.sf.jasperreports.engine.JRException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class ReportServiceImplTest {

    @InjectMocks
    private ReportServiceImpl reportService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private SubCategoryRepository subCategoryRepository;

    @Mock
    private SimpleReportExporter reportExporter;

    @Mock
    private SimpleReportFiller simpleReportFiller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void generateCategoriesExcelReport() throws JRException {
        // Mock data
        when(categoryRepository.findAll()).thenReturn(new ArrayList<>());
        when(reportExporter.exportReportToByteArray(any(), anyString(), anyString())).thenReturn(new byte[]{});

        // Test
        FileDTO result = reportService.generateCategoriesExcelReport();

        // Assertions
        assertNotNull(result);
        // Add more assertions based on your expectations
    }

    @Test
    void generateSubCategoriesExcelReport() throws JRException {
        // Mock data
        when(subCategoryRepository.findAll()).thenReturn(new ArrayList<>());
        when(reportExporter.exportReportToByteArray(any(), anyString(), anyString())).thenReturn(new byte[]{});

        // Test
        FileDTO result = reportService.generateSubCategoriesExcelReport();

        // Assertions
        assertNotNull(result);
        // Add more assertions based on your expectations
    }

    @Test
    void generatePdfFullReport() throws JRException {
        // Mock data
        when(categoryRepository.findAll()).thenReturn(new ArrayList<>());
        when(subCategoryRepository.findAll()).thenReturn(new ArrayList<>());
        when(reportExporter.exportReportToByteArray(any(), anyString(), anyString())).thenReturn(new byte[]{});

        // Test
        FileDTO result = reportService.generatePdfFullReport();

        // Assertions
        assertNotNull(result);
        // Add more assertions based on your expectations
    }

    @Test
    void generateAndZipReports() throws JRException, IOException {
        // Mock data
        when(categoryRepository.findAll()).thenReturn(new ArrayList<>());
        when(subCategoryRepository.findAll()).thenReturn(new ArrayList<>());
        when(reportExporter.exportReportToByteArray(any(), anyString(), anyString())).thenReturn(new byte[]{});
        when(reportExporter.zipJasperPrintList(any())).thenReturn(new byte[]{});

        // Test
        FileDTO result = reportService.generateAndZipReports();

        // Assertions
        assertNotNull(result);
        // Add more assertions based on your expectations
    }

    @Test
    void generateMultiSheetExcelReport() throws JRException {
        // Mock data
        when(categoryRepository.findAll()).thenReturn(new ArrayList<>());
        when(subCategoryRepository.findAll()).thenReturn(new ArrayList<>());
        when(reportExporter.exportReportToByteArray(any(), anyString(), anyString())).thenReturn(new byte[]{});

        // Mock the categorySubDataSource
        JRBeanCollectionDataSource categorySubDataSource = mock(JRBeanCollectionDataSource.class);
        when(reportExporter.getSubReportDataSource(any())).thenReturn(categorySubDataSource);

        // Test
        FileDTO result = reportService.generateMultiSheetExcelReport();

        // Assertions
        assertNotNull(result);
        // Add more assertions based on your expectations
    }


    @Test
    void exportToCsv() throws IOException {
        // Arrange: Mock Category and SubCategory data
        Category category1 = new Category("Category1", "Category1");
        category1.setUid(UUIDGenerator.generateType1UUID().toString());
        Category category2 = new Category("Category2", "Category2");
        category2.setUid(UUIDGenerator.generateType1UUID().toString());

        SubCategory subCategory1 = new SubCategory("SubCategory1", category1.getDescription());
        subCategory1.setUid(UUIDGenerator.generateType1UUID().toString());
        subCategory1.setCategory(category1);
        SubCategory subCategory2 = new SubCategory("SubCategory2", category2.getDescription());
        subCategory2.setUid(UUIDGenerator.generateType1UUID().toString());
        subCategory2.setCategory(category2);
        SubCategory subCategory3 = new SubCategory("SubCategory3", category1.getDescription());
        subCategory3.setUid(UUIDGenerator.generateType1UUID().toString());
        subCategory3.setCategory(category2);

        when(categoryRepository.findAll()).thenReturn(List.of(category1, category2));
        when(subCategoryRepository.findAll()).thenReturn(List.of(subCategory1, subCategory2, subCategory3));

        // Act
        FileDTO fileDTO = reportService.exportToCsv();

        // Assert
        assertNotNull(fileDTO);
        assertNotNull(fileDTO.getFileContent());
        assertNotNull(fileDTO.getFileName());
        assertTrue(fileDTO.getFileName().startsWith("Data_"));

        String csvContent = new String(Base64.decodeBase64(fileDTO.getFileContent()), StandardCharsets.UTF_8);
        String[] csvLines = csvContent.split("\n");

        assertEquals("Category Name,Subcategory Name,Total Subcategories", csvLines[0]); // Check the header

        // Check the data rows
        assertEquals("Category1,N/A,1", csvLines[1]);
        assertEquals("Category2,N/A,2", csvLines[2]);
        assertEquals("Category1,SubCategory1,N/A", csvLines[3]);
        assertEquals("Category2,SubCategory2,N/A", csvLines[4]);
        assertEquals("Category2,SubCategory3,N/A", csvLines[5]);
    }


    @Test
    void exportToHtml() throws IOException {
        // Arrange: Mock data
        List<Category> categories = new ArrayList<>();
        List<SubCategory> subCategories = new ArrayList<>();
        categories.add(new Category("Category 1","Category 1"));
        subCategories.add(new SubCategory("SubCategory 1", "SubCategory 1"));

        when(categoryRepository.findAll()).thenReturn(categories);
        when(subCategoryRepository.findAll()).thenReturn(subCategories);

        // Act
        FileDTO fileDTO = reportService.exportToHtml();

        // Assert
        assertNotNull(fileDTO);
        assertEquals("Data_" + Utils.getCurrentDateAsString() + ".html", fileDTO.getFileName());
        assertNotNull(fileDTO.getFileContent());
        assertTrue(fileDTO.getFileContent().contains("Category 1"));
        assertTrue(fileDTO.getFileContent().contains("SubCategory 1"));
    }
}

