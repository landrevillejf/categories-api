package com.protonmail.landrevillejf.cognos.categories.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.CategoryReportDTO;
import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.FileDTO;
import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.SubCategoryReportDTO;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;
import com.protonmail.landrevillejf.cognos.categories.api.repository.CategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.repository.SubCategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.service.ReportService;
import com.protonmail.landrevillejf.cognos.categories.api.util.EntityDtoMapper;
import com.protonmail.landrevillejf.cognos.categories.api.util.Utils;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.ExecutionTime;
import com.protonmail.landrevillejf.cognos.categories.api.util.jasperreport.SimpleReportExporter;
import com.protonmail.landrevillejf.cognos.categories.api.util.jasperreport.SimpleReportFiller;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ObjectMapper mapper;
    private final SimpleReportExporter reportExporter;
    private final SimpleReportFiller simpleReportFiller;


    @ExecutionTime
    @Override
    public FileDTO generateCategoriesExcelReport() throws JRException {

        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryReportDTO> reportRecords = EntityDtoMapper.convertToDtoList(categoryList, CategoryReportDTO.class);

        for (Category category : categoryList) {
            CategoryReportDTO reportDTO = EntityDtoMapper.convertToDto(category, CategoryReportDTO.class);
            reportRecords.add(reportDTO);
        }

        String dateAsString = Utils.getCurrentDateAsString();
        String fileName = "Category_Report_" + dateAsString + ".xlsx";

        byte[] reportAsByteArray = reportExporter.exportReportToByteArray(
                reportRecords, fileName, "jrxml/excel/categoriesExcelReport");

        String base64String = Base64.encodeBase64String(reportAsByteArray);

        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileContent(base64String);
        fileDTO.setFileName(fileName);

        return fileDTO;
    }

    @Override
    public FileDTO generateSubCategoriesExcelReport() throws JRException {
        List<SubCategory> subCategoryList = subCategoryRepository.findAll();
        List<SubCategoryReportDTO> reportRecords = new ArrayList<>();

        for (SubCategory subCategory : subCategoryList) {
            SubCategoryReportDTO reportDTO = EntityDtoMapper.convertToDto(subCategory, SubCategoryReportDTO.class);
            int totalSubcategories = calculateTotalSubcategories(subCategory);
            reportDTO.setTotalSubcategories(totalSubcategories);
            reportRecords.add(reportDTO);
        }

        String dateAsString = Utils.getCurrentDateAsString();
        String fileName = "SubCategory_Report_" + dateAsString + ".xlsx";

        byte[] reportAsByteArray = reportExporter.exportReportToByteArray(
                reportRecords, fileName, "jrxml/excel/subcategoriesExcelReport");

        String base64String = Base64.encodeBase64String(reportAsByteArray);

        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileContent(base64String);
        fileDTO.setFileName(fileName);

        return fileDTO;
    }

    private int calculateTotalSubcategories(SubCategory subCategory) {
        return (int) subCategoryRepository.count();
    }

    @ExecutionTime
    @Override
    public FileDTO generatePdfFullReport() throws JRException {

        List<Category> categoriesMainReport = categoryRepository.findAll();
        List<CategoryReportDTO> mainReportRecords =
                Collections.singletonList(
                        EntityDtoMapper.convertToDto(categoriesMainReport, CategoryReportDTO.class));

        List<SubCategory> subcategoryListSubReport = subCategoryRepository.findAll();
        List<SubCategoryReportDTO> subReportRecords =
                Collections.singletonList(
                        EntityDtoMapper.convertToDto(subcategoryListSubReport, SubCategoryReportDTO.class));

        String dateAsString = Utils.getCurrentDateAsString();
        String fileName = "Full_Report_" + dateAsString + ".pdf";

        // prepare the sub report
        JasperReport subReport = simpleReportFiller.compileReport("jrxml/pdf/subReport");
        JRBeanCollectionDataSource subDataSource = reportExporter.getSubReportDataSource(subReportRecords);

        // add the sub report as parameter to the main report
        Map<String, Object> jasperParameters = new HashMap<>();
        jasperParameters.put("subReport", subReport);
        jasperParameters.put("subDataSource", subDataSource);

        byte[] reportAsByteArray = reportExporter.exportReportToByteArray(
                mainReportRecords,
                jasperParameters,
                fileName,
                "jrxml/pdf/mainReport");

        String base64String = Base64.encodeBase64String(reportAsByteArray);

        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileContent(base64String);
        fileDTO.setFileName(fileName);

        return fileDTO;
    }

    @Override
    public FileDTO generateAndZipReports() throws JRException, IOException {

        String dateAsString = Utils.getCurrentDateAsString();

        List<SubCategory> subcategoryListSubReport = subCategoryRepository.findAll();
        List<SubCategoryReportDTO> subReportRecords =
                Collections.singletonList(
                        EntityDtoMapper.convertToDto(subcategoryListSubReport, SubCategoryReportDTO.class));
        String subCategoryFileName = "SubCategory_Report_" + dateAsString + ".xlsx";

        JasperPrint jasperPrintSubCategories = reportExporter.extractResultsToJasperPrint(
                subReportRecords, subCategoryFileName, "jrxml/excel/subcategoriesExcelReport");

        List<Category> categoriesMainReport = categoryRepository.findAll();
        List<CategoryReportDTO> mainReportRecords =
                Collections.singletonList(
                        EntityDtoMapper.convertToDto(categoriesMainReport, CategoryReportDTO.class));
        String categoryFileName = "Category_Report_" + dateAsString + ".xlsx";

        JasperPrint jasperPrintCategories = reportExporter.extractResultsToJasperPrint(
                mainReportRecords, categoryFileName, "jrxml/excel/categoriesExcelReport");


        List<JasperPrint> listOfJasperPrints = new ArrayList<>();

        listOfJasperPrints.add(jasperPrintSubCategories);
        listOfJasperPrints.add(jasperPrintCategories);

        byte[] reportAsByteArray = reportExporter.zipJasperPrintList(listOfJasperPrints);


        String base64String = Base64.encodeBase64String(reportAsByteArray);

        String fileName = "Multiple_Reports_" + dateAsString + ".zip";

        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileContent(base64String);
        fileDTO.setFileName(fileName);

        return fileDTO;
    }

    @Override
    public FileDTO generateMultiSheetExcelReport() throws JRException {

        String dateAsString = Utils.getCurrentDateAsString();
        String excelFileName = "Multi_Sheet_Report_" + dateAsString + ".xlsx";

        List<Category> categoriesMainReport = categoryRepository.findAll();
        List<CategoryReportDTO> mainReportRecords =
                Collections.singletonList(
                        EntityDtoMapper.convertToDto(categoriesMainReport, CategoryReportDTO.class));

        List<SubCategory> subcategoryListSubReport = subCategoryRepository.findAll();
        List<SubCategoryReportDTO> subReportRecords =
                Collections.singletonList(
                        EntityDtoMapper.convertToDto(subcategoryListSubReport, SubCategoryReportDTO.class));

        // prepare categories sub report
        JasperReport categorySubReport = simpleReportFiller.compileReport("jrxml/excel/categoriesExcelReport");
        JRBeanCollectionDataSource categorySubDataSource = reportExporter.getSubReportDataSource(mainReportRecords);


        // prepare subcategories sub report
        JasperReport subCategorySubReport = simpleReportFiller.compileReport("jrxml/excel/subcategoriesExcelReport");
        JRBeanCollectionDataSource subCategorySubDataSource = reportExporter.getSubReportDataSource(subReportRecords);


        // add sub reports as parameters to Jasper Report
        Map<String, Object> jasperParameters = new HashMap<>();
        jasperParameters.put("categorySubReport", categorySubReport);
        jasperParameters.put("categorySubDataSource", categorySubDataSource);
        jasperParameters.put("subCategorySubReport", subCategorySubReport);
        jasperParameters.put("subCategorySubDataSource", subCategorySubDataSource);

        // set sheet names for each sub report
        jasperParameters.put("firstSheetName", "CATEGORIES_REPORT");
        jasperParameters.put("secondSheetName", "SUB_CATEGORIES_REPORT");


        byte[] reportAsByteArray = reportExporter.exportReportToByteArray(
                null, jasperParameters, excelFileName, "jrxml/excel/multiSheetExcelReport");

        String base64String = Base64.encodeBase64String(reportAsByteArray);


        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileContent(base64String);
        fileDTO.setFileName(excelFileName);

        return fileDTO;
    }
}
