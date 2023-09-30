package com.protonmail.landrevillejf.cognos.categories.api.util;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for exporting JasperReports to various formats.
 */
public class JasperReportUtils {
    private static final Logger logger = LoggerFactory.getLogger(JasperReportUtils.class);

    // Private constructor to prevent instantiation
    private JasperReportUtils() {
        throw new AssertionError("Utility class should not be instantiated.");
    }

    /**
     * Export a JasperPrint object to PDF format.
     *
     * @param jasperPrint The JasperPrint object to export.
     * @param fileName    The name of the PDF file to create.
     * @param author      The author metadata for the PDF.
     * @throws JRException If an error occurs during the export process.
     */
    public static void exportToPdf(JasperPrint jasperPrint, String fileName, String author) throws JRException {
        validateJasperPrint(jasperPrint);

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fileName));

        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);

        SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
        exportConfig.setMetadataAuthor(author);
        exportConfig.setEncrypted(true);
        exportConfig.setAllowedPermissionsHint("PRINTING");

        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);

        exporter.exportReport();
    }

    /**
     * Export a JasperPrint object to XLSX format.
     *
     * @param jasperPrint The JasperPrint object to export.
     * @param fileName    The name of the XLSX file to create.
     * @param sheetName   The name of the XLSX sheet.
     * @throws JRException If an error occurs during the export process.
     */
    public static void exportToXlsx(JasperPrint jasperPrint, String fileName, String sheetName) throws JRException {
        validateJasperPrint(jasperPrint);

        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fileName));

        SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
        reportConfig.setSheetNames(new String[] { sheetName });

        exporter.setConfiguration(reportConfig);

        exporter.exportReport();
    }

    /**
     * Export a JasperPrint object to CSV format.
     *
     * @param jasperPrint The JasperPrint object to export.
     * @param fileName    The name of the CSV file to create.
     * @throws JRException If an error occurs during the export process.
     */
    public static void exportToCsv(JasperPrint jasperPrint, String fileName) throws JRException {
        validateJasperPrint(jasperPrint);

        JRCsvExporter exporter = new JRCsvExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(fileName));

        exporter.exportReport();
    }

    /**
     * Export a JasperPrint object to HTML format.
     *
     * @param jasperPrint The JasperPrint object to export.
     * @param fileName    The name of the HTML file to create.
     * @throws JRException If an error occurs during the export process.
     */
    public static void exportToHtml(JasperPrint jasperPrint, String fileName) throws JRException {
        validateJasperPrint(jasperPrint);

        HtmlExporter exporter = new HtmlExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(fileName));

        exporter.exportReport();
    }

    /**
     * Validates a JasperPrint object to ensure it is not null.
     *
     * @param jasperPrint The JasperPrint object to validate.
     * @throws JRException If the JasperPrint object is null.
     */
    private static void validateJasperPrint(JasperPrint jasperPrint) throws JRException {
        if (jasperPrint == null) {
            throw new JRException("JasperPrint object is null. Ensure it is properly initialized.");
        }
    }
}


