package com.protonmail.landrevillejf.cognos.categories.api.service.impl;

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

import com.protonmail.landrevillejf.cognos.categories.api.util.jasperreport.simple.SimpleReportExporter;
import com.protonmail.landrevillejf.cognos.categories.api.util.jasperreport.simple.SimpleReportFiller;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for generating various reports.
 */
@SuppressWarnings("CheckStyle")
@AllArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {
    /**
     * Logger for this service.
     */
    private final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    /**
     * Repository for managing categories.
     */
    private final CategoryRepository categoryRepository;

    /**
     * Repository for managing subcategories.
     */
    private final SubCategoryRepository subCategoryRepository;

    /**
     * Report exporter for exporting reports.
     */
    private final SimpleReportExporter reportExporter;

    /**
     * Report filler for filling report templates.
     */
    private final SimpleReportFiller simpleReportFiller;

    /**
     * Export data to CSV with category and subcategory information.
     *
     * @return A FileDTO containing the exported CSV data.
     * @throws IOException If there is an error while exporting the data.
     */
    @ExecutionTime
    @Override
    public FileDTO exportToCsv() throws IOException {
        try {
            // Generate CSV data with category and subcategory information
            List<Category> categoryList = categoryRepository.findAll();
            List<SubCategory> subCategoryList = subCategoryRepository.findAll();

            StringBuilder csvData = new StringBuilder();
            csvData.append("Category Name,Subcategory Name,Total Subcategories\n");

            for (Category category : categoryList) {
                int totalSubcategories = subCategoryList.stream()
                        .filter(subCategory -> subCategory.getCategory().equals(category))
                        .mapToInt(subCategory -> 1)
                        .sum();

                csvData.append(category.getName()).append(",");
                csvData.append("N/A").append(","); // No subcategory name for categories
                csvData.append(totalSubcategories).append("\n");
            }

            for (SubCategory subCategory : subCategoryList) {
                csvData.append(subCategory.getCategory().getName()).append(",");
                csvData.append(subCategory.getName()).append(",");
                csvData.append("N/A").append("\n"); // No total subcategories for subcategories
            }

            String dateAsString = Utils.getCurrentDateAsString();
            String fileName = "Data_" + dateAsString + ".csv";

            byte[] csvBytes = csvData.toString().getBytes(StandardCharsets.UTF_8);

            String base64String = Base64.encodeBase64String(csvBytes);

            FileDTO fileDTO = new FileDTO();
            fileDTO.setFileContent(base64String);
            fileDTO.setFileName(fileName);

            return fileDTO;
        } catch (Exception e) {
            logger.error("Error exporting data to CSV: {}", e.getMessage(), e);
            throw new IOException("Error exporting data to CSV: " + e.getMessage(), e);
        }
    }

    /**
     * Export data to HTML with category and subcategory information.
     *
     * @return A FileDTO containing the exported HTML data.
     * @throws IOException If there is an error while exporting the data.
     */
    @ExecutionTime
    @Override
    public FileDTO exportToHtml() throws IOException {
        try {
            // Generate HTML data with category and subcategory information
            List<Category> categoryList = categoryRepository.findAll();
            List<SubCategory> subCategoryList = subCategoryRepository.findAll();

            StringBuilder htmlData = new StringBuilder();
            htmlData.append("<html><body><h1>Data with Category and Subcategory Information</h1>");
            htmlData.append("<table border='1'><tr><th>Category Name</th><th>Subcategory Name</th><th>Total Subcategories</th></tr>");

            for (Category category : categoryList) {
                int totalSubcategories = subCategoryList.stream()
                        .filter(subCategory -> subCategory.getCategory().equals(category))
                        .mapToInt(subCategory -> 1)
                        .sum();

                htmlData.append("<tr>");
                htmlData.append("<td>").append(category.getName()).append("</td>");
                htmlData.append("<td>").append("N/A").append("</td>"); // No subcategory name for categories
                htmlData.append("<td>").append(totalSubcategories).append("</td>");
                htmlData.append("</tr>");
            }

            for (SubCategory subCategory : subCategoryList) {
                htmlData.append("<tr>");
                htmlData.append("<td>").append(subCategory.getCategory().getName()).append("</td>");
                htmlData.append("<td>").append(subCategory.getName()).append("</td>");
                htmlData.append("<td>").append("N/A").append("</td>"); // No total subcategories for subcategories
                htmlData.append("</tr>");
            }

            htmlData.append("</table></body></html>");

            String dateAsString = Utils.getCurrentDateAsString();
            String fileName = "Data_" + dateAsString + ".html";

            byte[] htmlBytes = htmlData.toString().getBytes(StandardCharsets.UTF_8);

            String base64String = Base64.encodeBase64String(htmlBytes);

            FileDTO fileDTO = new FileDTO();
            fileDTO.setFileContent(base64String);
            fileDTO.setFileName(fileName);

            return fileDTO;
        } catch (Exception e) {
            logger.error("Error exporting data to HTML: {}", e.getMessage(), e);
            throw new IOException("Error exporting data to HTML: " + e.getMessage(), e);
        }
    }

    /**
     * Generate an Excel report containing category information.
     *
     * @return A FileDTO containing the generated Excel report.
     * @throws JRException If there is an error while generating the report.
     */
    @ExecutionTime
    @Override
    public FileDTO generateCategoriesExcelReport() throws JRException {
        FileDTO fileDTO;
        try {
            List<Category> categoryList = categoryRepository.findAll();
            List<CategoryReportDTO> reportRecords = new ArrayList<>();

            for (Category category : categoryList) {
                CategoryReportDTO categoryReportDTO = EntityDtoMapper.convertToDto(category, CategoryReportDTO.class);
                int totalSubcategories = subCategoryRepository.countByCategory(category);
                categoryReportDTO.setTotalSubcategories(totalSubcategories);
                categoryReportDTO.setCreatedAtFormatted(categoryReportDTO.getFormattedCreatedAt());
                categoryReportDTO.setUpdatedAtFormatted(categoryReportDTO.getFormattedUpdatedAt());

                reportRecords.add(categoryReportDTO);
            }

            String dateAsString = Utils.getCurrentDateAsString();
            String fileName = "Category_Report_" + dateAsString + ".xlsx";

            byte[] reportAsByteArray = reportExporter.exportReportToByteArray(
                    reportRecords, fileName, "jrxml/excel/categoriesExcelReport");

            String base64String = Base64.encodeBase64String(reportAsByteArray);

            fileDTO = new FileDTO();
            fileDTO.setFileContent(base64String);
            fileDTO.setFileName(fileName);
        } catch (JRException e) {
            logger.error("Error: {}", e.getMessage(), e);
            throw new JRException("Error: {}", e.getMessage(), e);
        }

        return fileDTO;
    }

    /**
     * Generate an Excel report containing subcategory information.
     *
     * @return A FileDTO containing the generated Excel report.
     * @throws JRException If there is an error while generating the report.
     */
    @ExecutionTime
    @Override
    public FileDTO generateSubCategoriesExcelReport() throws JRException {
        FileDTO fileDTO;
        try {
            List<SubCategory> subCategoryList = subCategoryRepository.findAll();
            List<SubCategoryReportDTO> reportRecords = new ArrayList<>();

            for (SubCategory subCategory : subCategoryList) {
                SubCategoryReportDTO subCategoryReportDTO = EntityDtoMapper.convertToDto(subCategory, SubCategoryReportDTO.class);
                subCategoryReportDTO.setCategory(subCategory.getCategory().getName());
                subCategoryReportDTO.setCreatedAtFormatted(subCategoryReportDTO.getFormattedCreatedAt());
                subCategoryReportDTO.setUpdatedAtFormatted(subCategoryReportDTO.getFormattedUpdatedAt());
                reportRecords.add(subCategoryReportDTO);
            }

            String dateAsString = Utils.getCurrentDateAsString();
            String fileName = "SubCategory_Report_" + dateAsString + ".xlsx";

            byte[] reportAsByteArray = reportExporter.exportReportToByteArray(
                    reportRecords, fileName, "jrxml/excel/subcategoriesExcelReport");

            String base64String = Base64.encodeBase64String(reportAsByteArray);

            fileDTO = new FileDTO();
            fileDTO.setFileContent(base64String);
            fileDTO.setFileName(fileName);
        } catch (JRException e) {
            logger.error("Error: {}", e.getMessage(), e);
            throw new JRException("Error: {}", e.getMessage(), e);
        }

        return fileDTO;
    }

    /**
     * Generate a PDF report containing both category and subcategory information.
     *
     * @return A FileDTO containing the generated PDF report.
     * @throws JRException If there is an error while generating the report.
     */
    @ExecutionTime
    @Override
    public FileDTO generatePdfFullReport() throws JRException {
        FileDTO fileDTO;
        try {
            List<Category> categoryList = categoryRepository.findAll();
            List<CategoryReportDTO> mainReportRecords = new ArrayList<>();

            for (Category category : categoryList) {
                CategoryReportDTO categoryReportDTO = EntityDtoMapper.convertToDto(category, CategoryReportDTO.class);
                int totalSubcategories = subCategoryRepository.countByCategory(category);
                categoryReportDTO.setTotalSubcategories(totalSubcategories);
                categoryReportDTO.setCreatedAtFormatted(categoryReportDTO.getFormattedCreatedAt());
                categoryReportDTO.setUpdatedAtFormatted(categoryReportDTO.getFormattedUpdatedAt());

                mainReportRecords.add(categoryReportDTO);
            }

            List<SubCategory> subCategoryList = subCategoryRepository.findAll();
            List<SubCategoryReportDTO> subReportRecords = new ArrayList<>();

            for (SubCategory subCategory : subCategoryList) {
                SubCategoryReportDTO subCategoryReportDTO = EntityDtoMapper.convertToDto(subCategory, SubCategoryReportDTO.class);
                subCategoryReportDTO.setCategory(subCategory.getCategory().getName());
                subCategoryReportDTO.setCreatedAtFormatted(subCategoryReportDTO.getFormattedCreatedAt());
                subCategoryReportDTO.setUpdatedAtFormatted(subCategoryReportDTO.getFormattedUpdatedAt());
                subReportRecords.add(subCategoryReportDTO);
            }

            String dateAsString = Utils.getCurrentDateAsString();
            String fileName = "Full_Report_" + dateAsString + ".pdf";

            JasperReport subReport = simpleReportFiller.compileReport("jrxml/pdf/subReport");
            JRBeanCollectionDataSource subDataSource = reportExporter.getSubReportDataSource(subReportRecords);

            Map<String, Object> jasperParameters = new HashMap<>();
            jasperParameters.put("subReport", subReport);
            jasperParameters.put("subDataSource", subDataSource);

            byte[] reportAsByteArray = reportExporter.exportReportToByteArray(
                    mainReportRecords,
                    jasperParameters,
                    fileName,
                    "jrxml/pdf/mainReport");

            String base64String = Base64.encodeBase64String(reportAsByteArray);

            fileDTO = new FileDTO();
            fileDTO.setFileContent(base64String);
            fileDTO.setFileName(fileName);
        } catch (JRException e) {
            logger.error("Error: {}", e.getMessage(), e);
            throw new JRException("Error: {}", e.getMessage(), e);
        }

        return fileDTO;
    }

    /**
     * Generate multiple reports (subcategories and categories) and zip them into a single file.
     *
     * @return A FileDTO containing the generated zip file.
     * @throws JRException If there is an error while generating the reports.
     * @throws IOException  If there is an error while zipping the reports.
     */
    @ExecutionTime
    @Override
    public FileDTO generateAndZipReports() throws JRException, IOException {
        FileDTO fileDTO;
        try {
            String dateAsString = Utils.getCurrentDateAsString();

            List<Category> categoryList = categoryRepository.findAll();
            List<CategoryReportDTO> mainReportRecords = new ArrayList<>();

            for (Category category : categoryList) {
                CategoryReportDTO categoryReportDTO = EntityDtoMapper.convertToDto(category, CategoryReportDTO.class);
                int totalSubcategories = subCategoryRepository.countByCategory(category);
                categoryReportDTO.setTotalSubcategories(totalSubcategories);
                categoryReportDTO.setCreatedAtFormatted(categoryReportDTO.getFormattedCreatedAt());
                categoryReportDTO.setUpdatedAtFormatted(categoryReportDTO.getFormattedUpdatedAt());

                mainReportRecords.add(categoryReportDTO);
            }

            List<SubCategory> subCategoryList = subCategoryRepository.findAll();
            List<SubCategoryReportDTO> subReportRecords = new ArrayList<>();

            for (SubCategory subCategory : subCategoryList) {
                SubCategoryReportDTO subCategoryReportDTO = EntityDtoMapper.convertToDto(subCategory, SubCategoryReportDTO.class);
                subCategoryReportDTO.setCategory(subCategory.getCategory().getName());
                subCategoryReportDTO.setCreatedAtFormatted(subCategoryReportDTO.getFormattedCreatedAt());
                subCategoryReportDTO.setUpdatedAtFormatted(subCategoryReportDTO.getFormattedUpdatedAt());
                subReportRecords.add(subCategoryReportDTO);
            }

            String subCategoryFileName = "SubCategory_Report_" + dateAsString + ".xlsx";

            JasperPrint jasperPrintSubCategories = reportExporter.extractResultsToJasperPrint(
                    subReportRecords, subCategoryFileName, "jrxml/excel/subcategoriesExcelReport");

            String categoryFileName = "Category_Report_" + dateAsString + ".xlsx";

            JasperPrint jasperPrintCategories = reportExporter.extractResultsToJasperPrint(
                    mainReportRecords, categoryFileName, "jrxml/excel/categoriesExcelReport");


            List<JasperPrint> listOfJasperPrints = new ArrayList<>();

            listOfJasperPrints.add(jasperPrintSubCategories);
            listOfJasperPrints.add(jasperPrintCategories);

            byte[] reportAsByteArray = reportExporter.zipJasperPrintList(listOfJasperPrints);


            String base64String = Base64.encodeBase64String(reportAsByteArray);

            String fileName = "Multiple_Reports_" + dateAsString + ".zip";

            fileDTO = new FileDTO();
            fileDTO.setFileContent(base64String);
            fileDTO.setFileName(fileName);
        } catch (JRException e) {
            logger.error("Error: {}", e.getMessage(), e);
            throw new JRException("Error: {}", e.getMessage(), e);
        }

        return fileDTO;
    }

    /**
     * Generate a multi-sheet Excel report containing both category and subcategory information.
     *
     * @return A FileDTO containing the generated Excel report.
     * @throws JRException If there is an error while generating the report.
     */
    @ExecutionTime
    @Override
    public FileDTO generateMultiSheetExcelReport() throws JRException {
        FileDTO fileDTO = null;
        try {
            String dateAsString = Utils.getCurrentDateAsString();
            String excelFileName = "Multi_Sheet_Report_" + dateAsString + ".xlsx";

            List<Category> categoryList = categoryRepository.findAll();
            List<CategoryReportDTO> mainReportRecords = new ArrayList<>();

            for (Category category : categoryList) {
                CategoryReportDTO categoryReportDTO = EntityDtoMapper.convertToDto(category, CategoryReportDTO.class);
                int totalSubcategories = subCategoryRepository.countByCategory(category);
                categoryReportDTO.setTotalSubcategories(totalSubcategories);
                categoryReportDTO.setCreatedAtFormatted(categoryReportDTO.getFormattedCreatedAt());
                categoryReportDTO.setUpdatedAtFormatted(categoryReportDTO.getFormattedUpdatedAt());

                mainReportRecords.add(categoryReportDTO);
            }

            List<SubCategory> subCategoryList = subCategoryRepository.findAll();
            List<SubCategoryReportDTO> subReportRecords = new ArrayList<>();

            for (SubCategory subCategory : subCategoryList) {
                SubCategoryReportDTO subCategoryReportDTO = EntityDtoMapper.convertToDto(subCategory, SubCategoryReportDTO.class);
                subCategoryReportDTO.setCategory(subCategory.getCategory().getName());
                subCategoryReportDTO.setCreatedAtFormatted(subCategoryReportDTO.getFormattedCreatedAt());
                subCategoryReportDTO.setUpdatedAtFormatted(subCategoryReportDTO.getFormattedUpdatedAt());
                subReportRecords.add(subCategoryReportDTO);
            }

            JasperReport categorySubReport = simpleReportFiller.compileReport("jrxml/excel/categoriesExcelReport");
            JRBeanCollectionDataSource categorySubDataSource = reportExporter.getSubReportDataSource(mainReportRecords);

            JasperReport subCategorySubReport = simpleReportFiller.compileReport("jrxml/excel/subcategoriesExcelReport");
            JRBeanCollectionDataSource subCategorySubDataSource = reportExporter.getSubReportDataSource(subReportRecords);

            logger.info("Number of records in categorySubDataSource: " + categorySubDataSource.getRecordCount());
            logger.info("Number of records in subCategorySubDataSource: " + subCategorySubDataSource.getRecordCount());

            Map<String, Object> jasperParameters = new HashMap<>();
            jasperParameters.put("categorySubReport", categorySubReport);
            jasperParameters.put("categorySubDataSource", categorySubDataSource);
            jasperParameters.put("subCategorySubReport", subCategorySubReport);
            jasperParameters.put("subCategorySubDataSource", subCategorySubDataSource);
            jasperParameters.put("firstSheetName", "CATEGORIES_REPORT");
            jasperParameters.put("secondSheetName", "SUB_CATEGORIES_REPORT");


            byte[] reportAsByteArray = reportExporter.exportReportToByteArray(
                    null, jasperParameters, excelFileName, "jrxml/excel/multiSheetExcelReport");

            String base64String = Base64.encodeBase64String(reportAsByteArray);

            fileDTO = new FileDTO();
            fileDTO.setFileContent(base64String);
            fileDTO.setFileName(excelFileName);
        } catch (JRException e) {
            logger.error("Error: {}", e.getMessage(), e);
            throw new JRException("Error: {}", e.getMessage(), e);
        }

        return fileDTO;
    }
}
