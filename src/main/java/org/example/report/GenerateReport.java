package org.example.report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import okhttp3.internal.http.HttpHeaders;
import org.example.bean.Employee;
import org.example.enums.ExportType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class GenerateReport {

    public static void main (String args[]) throws JRException {

        String reportPath = "D:\\Content\\Report";

        Employee employee = new Employee();
        employee.setDate("22/04/2024");
        employee.setReference("RN000001");
        employee.setAmount("100000.00");
        employee.setStatus("ON");
        employee.setRemark("salary");

        Employee employee1 = new Employee();
        employee1.setDate("22/04/2024");
        employee1.setReference("RN000004");
        employee1.setAmount("200000.00");
        employee1.setStatus("ON");
        employee1.setRemark("salary");

        Employee employee2 = new Employee();
        employee2.setDate("22/04/2024");
        employee2.setReference("RN000099");
        employee2.setAmount("600000.00");
        employee2.setStatus("ON");
        employee2.setRemark("salary");

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        employeeList.add(employee1);
        employeeList.add(employee2);
        exportReport(employeeList,ExportType.PDF,reportPath);
        exportReport(employeeList,ExportType.EXCEL,reportPath);
    }

    private static void exportReport(Collection<?> beanCollection, ExportType exportType, String reportPath) throws JRException {
        InputStream transactionReportStream =
                GenerateReport.class
                        .getResourceAsStream(
                                "/assessment_2.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(transactionReportStream);
        JRBeanCollectionDataSource beanColDataSource =
                new JRBeanCollectionDataSource(beanCollection);
        HashMap<String, Object> parameters = new HashMap();

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);

        if (exportType == ExportType.PDF) {

            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\employee_list_report.pdf");

        } else if (exportType == ExportType.EXCEL) {

            JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE );
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE );
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER,Boolean.TRUE);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, reportPath+"\\employee_list_report.xls"); // Output file name
			exporter.exportReport();

        } else {
            throw new RuntimeException("File Format isn't supported!");
        }
    }
}
