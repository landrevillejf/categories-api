package com.protonmail.landrevillejf.cognos.categories.api.controller;


import com.protonmail.landrevillejf.cognos.categories.api.config.Api;
import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.FileDTO;
import com.protonmail.landrevillejf.cognos.categories.api.service.ReportService;
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

@Tag(name = "Report", description = "Reports management APIs")
@AllArgsConstructor
@RequestMapping(Api.REPORTS)
@RestController
public class ReportController {


    private final ReportService reportService;


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


}
