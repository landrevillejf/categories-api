package com.protonmail.landrevillejf.cognos.categories.api.service;

import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.FileDTO;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;

public interface ReportService {

    FileDTO generateCategoriesExcelReport() throws JRException;

    FileDTO generateSubCategoriesExcelReport() throws JRException;

    FileDTO generatePdfFullReport() throws JRException;

    FileDTO generateAndZipReports() throws JRException, IOException;

    FileDTO generateMultiSheetExcelReport() throws JRException;
}
