package com.protonmail.landrevillejf.cognos.categories.api.util.jasperreport.simple;


import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.CategoryReportDTO;
import com.protonmail.landrevillejf.cognos.categories.api.exception.ReportGenerationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Slf4j
@AllArgsConstructor
@Component
public class SimpleReportExporter {

    /**
     *
     */
    private final SimpleReportFiller simpleReportFiller;

    /**
     * Logger.
     */
    private final Logger logger = LoggerFactory.getLogger(SimpleReportExporter.class);

    /**
     * This method is used to generate a <strong>JasperPrint</strong> by providing the
     * list of records, the name of the generated file and the name of the jrxml file
     * which will be used as a template to be populated from the records of the list.
     *
     * @param records to be inserted in the file
     * @param jasperParameters this attribute can be used to add additional parameters to JasperReport ( example: local time for the Jasper Report )
     * @param reportFileName with the file extension. Currently supported ( .xlsx and .pdf ).
     * @param jrxmlFileName along with the path to it
     * @return JasperPrint
     */
    public JasperPrint extractResultsToJasperPrint(List<?> records, Map<String, Object> jasperParameters, String reportFileName, String jrxmlFileName) {

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(records);

        jasperParameters.put(JRParameter.REPORT_LOCALE, Locale.US);    // set Locale to Italy

        try {
            JasperPrint jasperPrint = simpleReportFiller.prepareReport(jrxmlFileName, jasperParameters, dataSource);
            jasperPrint.setName(reportFileName);

            return jasperPrint;

        } catch (JRException e) {

            log.error("Error generating report occurred in extractResultsToJasperPrint method.", e);
            throw new ReportGenerationException("Error generating report occurred in extractResultsToJasperPrint method.", e);

        } finally {
            dataSource.cloneDataSource();
        }
    }

    /**
     * This method is used to generate a <strong>JasperPrint</strong> by providing the
     * list of records, the name of the generated file and the name of the jrxml file
     * which will be used as a template to be populated from the records of the list.
     *
     * @param records to be inserted in the file
     * @param reportFileName with the file extension. Currently supported ( .xlsx and .pdf ).
     * @param jrxmlFileName along with the path to it
     * @return JasperPrint
     */
    public JasperPrint extractResultsToJasperPrint(List<?> records, String reportFileName, String jrxmlFileName) {
        return this.extractResultsToJasperPrint(records, new HashMap<>(), reportFileName, jrxmlFileName);
    }

    /**
     * Exports the generated report object received as parameter into Excel format and
     * returns the binary content as a byte array.
     *
     * @param jasperPrint report object to export
     * @return byte array representing the resulting PDF content
     * @throws JRException
     */
    public byte[] exportReportToExcel(JasperPrint jasperPrint) throws JRException {

        if (jasperPrint == null) {
            throw new ReportGenerationException("Error generating report occurred in exportReportToExcel method because jasperPrint is null.");
        }

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            JRXlsxExporter exporter = new JRXlsxExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));

            exporter.exportReport();
            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            throw new ReportGenerationException("Error occurred while exporting report to Excel format", e);
        }
    }

    /**
     * Exports the generated report object received as parameter into Excel or PDF and
     * returns the binary content as a byte array.
     *
     * @param jasperPrint
     * @return byte array representing the resulting file content
     * @throws JRException
     */
    public byte[] exportJasperPrintToByteArray(JasperPrint jasperPrint) throws JRException {


        if (jasperPrint == null) {
            throw new ReportGenerationException("Error generating report occurred in exportReportToExcel method because jasperPrint is null.");
        }

        String extension = Optional.ofNullable(jasperPrint.getName())
                .map(name -> name.substring(name.lastIndexOf(".") + 1).toLowerCase())
                .orElse("<no-extension>");

        if (extension.equals("xlsx")) {
            return exportReportToExcel(jasperPrint);

        } else if (extension.equals("pdf")) {
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } else {
            throw new ReportGenerationException("Currently '" + extension + "' format cannot be exported to byte[].");
        }
    }

    /**
     * This method is used when we want to put a sub-report inside our generated file, and we have to specify a data source.
     * @param records
     * @return
     */
    public JRBeanCollectionDataSource getSubReportDataSource(List<?> records) {
        return new JRBeanCollectionDataSource(records);
    }

    /**
     * This method is used to export a report to byte array
     *
     * @param records
     * @param fileName
     * @param jrxmlFileLocation
     * @return report as a byte array
     * @throws JRException
     */
    public byte[] exportReportToByteArray(List<?> records, String fileName, String jrxmlFileLocation) throws JRException {
        JasperPrint jasperPrint = this.extractResultsToJasperPrint(records, fileName, jrxmlFileLocation);
        return this.getReportByteArray(jasperPrint);
    }


    /**
     * This method is used to export a report to byte array
     * and also provide jasper parameters (example: provide sub-report, additional variables etc.)
     *
     * @param recordsOfMainReport
     * @param jasperParameters
     * @param fileName
     * @param jrxmlFileLocation
     * @return report as a byte array
     * @throws JRException
     */
    public byte[] exportReportToByteArray(
            List<?> recordsOfMainReport, Map<String, Object> jasperParameters, String fileName, String jrxmlFileLocation)
            throws JRException {
        JasperPrint jasperPrint = this.extractResultsToJasperPrint(recordsOfMainReport, jasperParameters, fileName, jrxmlFileLocation);
        return this.getReportByteArray(jasperPrint);
    }


    private byte[] getReportByteArray(JasperPrint jasperPrint) throws JRException {

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                this.exportJasperPrintToByteArray(jasperPrint))) {

            byte[] bytes = new byte[byteArrayInputStream.available()];
            byteArrayInputStream.read(bytes);
            return bytes;

        } catch (IOException e) {
            throw new JRException("Error converting report to byte array", e);
        }
    }

    /**
     * This method is used to zip a List&lt;JasperPrint&gt; and then return them as a byte[].
     *
     * @param listOfJasperPrints List of JasperPrint objects to be zipped.
     * @return Zipped list as a byte array.
     * @throws IOException If there is an I/O error while zipping.
     * @throws JRException If there is an error with JasperReports.
     */
    public byte[] zipJasperPrintList(List<JasperPrint> listOfJasperPrints) throws IOException, JRException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipFile = new ZipOutputStream(byteArrayOutputStream);

        for (JasperPrint listOfJasperPrint : listOfJasperPrints) {

            byte[] reportAsBytes = null;
            byte[] buffer = new byte[1024];

            reportAsBytes = this.exportJasperPrintToByteArray(listOfJasperPrint);

            ByteArrayInputStream fis = new ByteArrayInputStream(reportAsBytes);
            zipFile.putNextEntry(new ZipEntry(listOfJasperPrint.getName()));
            int length;

            while ((length = fis.read(buffer, 0, 1024)) > 0) {
                zipFile.write(buffer, 0, length);
            }

            zipFile.closeEntry();

            // close the InputStream
            fis.close();

        }

        zipFile.close();
        return byteArrayOutputStream.toByteArray();
    }


    public JRBeanCollectionDataSource getMainReportDataSource(List<CategoryReportDTO> records) {
        return new JRBeanCollectionDataSource(records);
    }



}
