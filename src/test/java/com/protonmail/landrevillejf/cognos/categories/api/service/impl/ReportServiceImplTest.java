package com.protonmail.landrevillejf.cognos.categories.api.service.impl;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.FileDTO;
import com.protonmail.landrevillejf.cognos.categories.api.repository.CategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.repository.SubCategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.util.jasperreport.SimpleReportExporter;
import com.protonmail.landrevillejf.cognos.categories.api.util.jasperreport.SimpleReportFiller;
import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
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

}

