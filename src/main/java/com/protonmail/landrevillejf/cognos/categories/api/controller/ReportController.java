/**
 * Controller class responsible for handling requests related to report generation.
 */
package com.protonmail.landrevillejf.cognos.categories.api.controller;


import com.protonmail.landrevillejf.cognos.categories.api.config.Api;
import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.FileDTO;
import com.protonmail.landrevillejf.cognos.categories.api.service.ReportService;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("CheckStyle")
@Author(name = "Jean-Francois Landreville",
        enterprise = "Lanaforge Inc.",
        email = "landrevillejf@protonmail.com",
        website = "https://www.lanaforge.ca"
)
@Maintainer(name = "Jean-Francois Landreville",
        enterprise = "Lanaforge Inc.",
        email = "landrevillejf@protonmail.com",
        website = "https://www.lanaforge.ca"
)
@Revision(
        date = "2023-09-25",
        revision = 1,
        comments = "Author ReportController"
)
@Tag(name = "Report", description = "Reports management APIs")
@AllArgsConstructor
@RequestMapping(Api.REPORTS)
@RestController
public class ReportController {


    private final ReportService reportService;

    /**
     * Generates an Excel report containing all the categories.
     *
     * @return ResponseEntity with the generated Excel report as an InputStreamResource.
     * @throws JRException if an error occurs during report generation.
     */
    @Operation(summary = "Generate an Excel report containing all the categories")
    @GetMapping(Api.EXCEL_CATEGORIES_REPORT)
    public ResponseEntity<InputStreamResource> generateCategoriesExcelReport() throws JRException {

        FileDTO report = reportService.generateCategoriesExcelReport();

        byte[] file = Base64.decodeBase64(report.getFileContent());
        InputStream targetStream = new ByteArrayInputStream(file);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=".concat(report.getFileName()));

        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .contentLength(file.length)
                .body(new InputStreamResource(targetStream));

    }

    /**
     * Generates an Excel report containing all the subcategories.
     *
     * @return ResponseEntity with the generated Excel report as an InputStreamResource.
     * @throws JRException if an error occurs during report generation.
     */
    @Operation(summary = "Generate an Excel report containing all the subcategories")
    @GetMapping(Api.EXCEL_SUB_CATEGORIES_REPORT)
    public ResponseEntity<InputStreamResource> generateSubCategoriesExcelReport() throws JRException {

        FileDTO report = reportService.generateSubCategoriesExcelReport();

        byte[] file = Base64.decodeBase64(report.getFileContent());
        InputStream targetStream = new ByteArrayInputStream(file);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=".concat(report.getFileName()));

        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .contentLength(file.length)
                .body(new InputStreamResource(targetStream));

    }

    /**
     * Generates a PDF report containing all the categories along with all the subcategories.
     *
     * @return ResponseEntity with the generated PDF report as an InputStreamResource.
     * @throws JRException if an error occurs during report generation.
     */
    @Operation(summary = "Generate a PDF report containing all the categories along with all the subcategories")
    @GetMapping(Api.PDF_FULL_REPORT)
    public ResponseEntity<InputStreamResource> generatePdfFullReport() throws JRException {

        FileDTO report = reportService.generatePdfFullReport();

        byte[] file = Base64.decodeBase64(report.getFileContent());
        InputStream targetStream = new ByteArrayInputStream(file);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=".concat(report.getFileName()));

        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .contentLength(file.length)
                .body(new InputStreamResource(targetStream));

    }

    /**
     * Generates a zip file containing two excel reports.
     *
     * @return ResponseEntity with the generated zip file as an InputStreamResource.
     * @throws JRException if an error occurs during report generation.
     * @throws IOException  if an I/O error occurs during zip file creation.
     */
    @Operation(summary = "Generate a zip file which contains two excel reports")
    @GetMapping(Api.ZIP_REPORT)
    public ResponseEntity<InputStreamResource> generateAndZipReports() throws JRException, IOException {

        FileDTO report = reportService.generateAndZipReports();

        byte[] file = Base64.decodeBase64(report.getFileContent());
        InputStream targetStream = new ByteArrayInputStream(file);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=".concat(report.getFileName()));

        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .contentLength(file.length)
                .body(new InputStreamResource(targetStream));

    }

    /**
     * Generates a multi-sheet Excel report containing categories and subcategories.
     *
     * @return ResponseEntity with the generated multi-sheet Excel report as an InputStreamResource.
     * @throws JRException if an error occurs during report generation.
     */
    @Operation(summary = "Generate a multi-sheet Excel report containing categories and subcategories")
    @GetMapping(Api.MULTI_SHEET_EXCEL_REPORT)
    public ResponseEntity<InputStreamResource> generateMultiSheetExcelReport() throws JRException {

        FileDTO report = reportService.generateMultiSheetExcelReport();

        byte[] file = Base64.decodeBase64(report.getFileContent());
        InputStream targetStream = new ByteArrayInputStream(file);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=".concat(report.getFileName()));

        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .contentLength(file.length)
                .body(new InputStreamResource(targetStream));
    }

    /*@Operation(summary = "Export data to CSV")
    @GetMapping(Api.EXPORT_TO_CSV)
    public ResponseEntity<InputStreamResource> exportToCsv() throws IOException {
        FileDTO report = reportService.exportToCsv();
        return createResponseEntity(report);
    }

    @Operation(summary = "Export data to HTML")
    @GetMapping(Api.EXPORT_TO_HTML)
    public ResponseEntity<InputStreamResource> exportToHtml() throws IOException {
        FileDTO report = reportService.exportToHtml();
        return createResponseEntity(report);
    }

    private ResponseEntity<InputStreamResource> createResponseEntity(FileDTO report) {
        byte[] file = report.getFileContent().getBytes(StandardCharsets.UTF_8);
        InputStream targetStream = new ByteArrayInputStream(file);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=" + report.getFileName());
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length)
                .body(new InputStreamResource(targetStream));
    }*/
}
