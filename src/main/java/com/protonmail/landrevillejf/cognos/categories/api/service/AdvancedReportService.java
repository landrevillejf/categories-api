package com.protonmail.landrevillejf.cognos.categories.api.service;

import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.FileDTO;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.ExecutionTime;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Creation;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;

/**
 * An interface for generating various reports.
 */
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
@Creation(
        date = "2023-10-01",
        comments = "AdvancedReportService Interface"
)
public interface AdvancedReportService {

    /**
     * Export to CSV.
     *
     * @throws JRException If there is an error during report generation.
     */
    @ExecutionTime
    FileDTO exportToCsv() throws JRException;

    /**
     * Export to HTML.
     *
     * @throws JRException If there is an error during report generation.
     */
    @ExecutionTime
    FileDTO exportToHtml() throws JRException;

    /**
     * Generates an Excel report.
     *
     * @return A FileDTO representing the generated Excel report.
     * @throws JRException If there is an error during report generation.
     */
    @ExecutionTime
    FileDTO exportToXlsx() throws JRException;

    /**
     * Generates a PDF report.
     *
     * @return A FileDTO representing the generated PDF report.
     * @throws JRException If there is an error during report generation.
     */
    @ExecutionTime
    FileDTO exportToPdf() throws JRException;

    /**
     * Generates and zips multiple reports into a single zip file.
     *
     * @return A FileDTO representing the generated zip file containing reports.
     * @throws JRException If there is an error during report generation.
     * @throws IOException  If there is an error during zip file creation.
     */
    @ExecutionTime
    FileDTO generateAndZipReports() throws JRException, IOException;

    /**
     * Generates a multi-sheet Excel report containing both categories and subcategories.
     *
     * @return A FileDTO representing the generated multi-sheet Excel report.
     * @throws JRException If there is an error during report generation.
     */
    @ExecutionTime
    FileDTO generateMultiSheetExcelReport() throws JRException;
}

