package com.protonmail.landrevillejf.cognos.categories.api.util.jasperreport.simple;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;

@SuppressWarnings("CheckStyle")
@Component
public class SimpleReportFiller {

    /**
     *
     * @param templateFileName
     * @param parameters
     * @param dataSource
     * @return
     * @throws JRException
     */
    public JasperPrint prepareReport(
            String templateFileName, Map<String, Object> parameters, JRBeanCollectionDataSource dataSource)
            throws JRException {
        JasperReport report = compileReport(templateFileName);
        return fillReport(report, parameters, dataSource);
    }

    /**
     *
     * @param templateFileName
     * @return
     * @throws JRException
     */
    public JasperReport compileReport(String templateFileName) throws JRException {
        InputStream reportStream = getClass().getResourceAsStream("/".concat(templateFileName).concat(".jrxml"));
        return JasperCompileManager.compileReport(reportStream);

    }

    /**
     *
     * @param report
     * @param parameters
     * @param dataSource
     * @return
     * @throws JRException
     */
    public JasperPrint fillReport(
            JasperReport report, Map<String, Object> parameters, JRBeanCollectionDataSource dataSource)
            throws JRException {
        return JasperFillManager.fillReport(report, parameters, dataSource);
    }
}
