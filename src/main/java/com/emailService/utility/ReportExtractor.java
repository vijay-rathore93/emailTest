package com.emailService.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.emailService.model.ProductDetails;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class ReportExtractor {

	public String exportReport(String reportFormat, List<ProductDetails> list)
			throws FileNotFoundException, JRException {
		String path = "classpath:/report";
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:productDetails.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
		Map<String, Object> parameters = new HashMap<>();
		//parameters.put("createdBy", "Java Techie");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
		}
		if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, "classpath:employees.pdf");
		}

		return "report generated in path : " + path;
	}
}
