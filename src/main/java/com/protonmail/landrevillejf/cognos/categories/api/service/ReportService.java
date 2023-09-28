package com.protonmail.landrevillejf.cognos.categories.api.service;

import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.FileDTO;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;

/**
 * An interface for generating various reports.
 */
public interface ReportService {

    /**
     * Generates an Excel report for categories.
     *
     * @return A FileDTO representing the generated Excel report.
     * @throws JRException If there is an error during report generation.
     */
    FileDTO generateCategoriesExcelReport() throws JRException;

    /**
     * Generates an Excel report for subcategories.
     *
     * @return A FileDTO representing the generated Excel report.
     * @throws JRException If there is an error during report generation.
     */
    FileDTO generateSubCategoriesExcelReport() throws JRException;

    /**
     * Generates a full PDF report containing both categories and subcategories.
     *
     * @return A FileDTO representing the generated PDF report.
     * @throws JRException If there is an error during report generation.
     */
    FileDTO generatePdfFullReport() throws JRException;

    /**
     * Generates and zips multiple reports into a single zip file.
     *
     * @return A FileDTO representing the generated zip file containing reports.
     * @throws JRException If there is an error during report generation.
     * @throws IOException  If there is an error during zip file creation.
     */
    FileDTO generateAndZipReports() throws JRException, IOException;

    /**
     * Generates a multi-sheet Excel report containing both categories and subcategories.
     *
     * @return A FileDTO representing the generated multi-sheet Excel report.
     * @throws JRException If there is an error during report generation.
     */
    FileDTO generateMultiSheetExcelReport() throws JRException;
}

