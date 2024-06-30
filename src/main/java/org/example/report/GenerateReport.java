package org.example.report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import org.example.bean.Employee;
import org.example.enums.ExportType;

import java.io.File;
import java.io.InputStream;
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

        File file = new File(reportPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (exportType == ExportType.PDF) {

            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\employee_list_report.pdf");

        } else if (exportType == ExportType.EXCEL) {

            JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, reportPath+"\\employee_list_report.xls"); // Output file name
			exporter.exportReport();

        } else {
            throw new RuntimeException("File Format isn't supported!");
        }
    }
}
