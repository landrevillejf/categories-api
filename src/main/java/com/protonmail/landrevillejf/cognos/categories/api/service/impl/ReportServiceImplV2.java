package com.protonmail.landrevillejf.cognos.categories.api.service.impl;

import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.CategoryReportDTO;
import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.FileDTO;
import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.SubCategoryReportDTO;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;
import com.protonmail.landrevillejf.cognos.categories.api.repository.CategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.repository.SubCategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.service.ReportService;
import com.protonmail.landrevillejf.cognos.categories.api.service.ReportServiceV2;
import com.protonmail.landrevillejf.cognos.categories.api.util.EntityDtoMapper;
import com.protonmail.landrevillejf.cognos.categories.api.util.Utils;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.ExecutionTime;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import com.protonmail.landrevillejf.cognos.categories.api.util.jasperreport.ReportExporter;
import com.protonmail.landrevillejf.cognos.categories.api.util.jasperreport.ReportFiller;
import com.protonmail.landrevillejf.cognos.categories.api.util.jasperreport.SimpleReportExporter;
import com.protonmail.landrevillejf.cognos.categories.api.util.jasperreport.SimpleReportFiller;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
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
@Revision(
        date = "2019-01-01",
        revision = 1,
        comments = "Author ReportServiceImpl"
)
@AllArgsConstructor
@Service
public class ReportServiceImplV2 implements ReportServiceV2 {

    private final Logger logger = LoggerFactory.getLogger(ReportService.class);
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ReportExporter reportExporter;
    //private final ReportFiller reportFiller;

    @Override
    public FileDTO exportToCsv() throws IOException {
        return null;
    }

    @Override
    public FileDTO exportToHtml() throws IOException {
        return null;
    }

    @Override
    public FileDTO generateCategoriesExcelReport() throws JRException {
        return null;
    }

    @Override
    public FileDTO generateSubCategoriesExcelReport() throws JRException {
        return null;
    }

    @Override
    public FileDTO generatePdfFullReport() throws JRException {
        return null;
    }

    @Override
    public FileDTO generateAndZipReports() throws JRException, IOException {
        return null;
    }

    @Override
    public FileDTO generateMultiSheetExcelReport() throws JRException {
        return null;
    }
}
