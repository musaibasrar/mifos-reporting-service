package org.ideoholic.mrs.jasper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ideoholic.mrs.model.Item;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Slf4j
@Service
public class JasperReportService {

  public byte[] getItemReport(List<Item> items, String format) {

    JasperReport jasperReport;

    try {
      jasperReport = (JasperReport)
          JRLoader.loadObject(ResourceUtils.getFile("item-report.jasper"));
    } catch (FileNotFoundException | JRException e) {
      try {
        File file = ResourceUtils.getFile("classpath:item-report.jrxml");
        jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRSaver.saveObject(jasperReport, "item-report.jasper");
      } catch (FileNotFoundException | JRException ex) {
        throw new RuntimeException(e);
      }
    }

    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(items);
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("title", "Item Report");
    JasperPrint jasperPrint = null;
    byte[] reportContent;

    try {
      jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
      switch (format) {
        case "pdf" -> reportContent = JasperExportManager.exportReportToPdf(jasperPrint);
        case "xml" -> reportContent = JasperExportManager.exportReportToXml(jasperPrint).getBytes();
        case "xls" -> reportContent = exportToXls(jasperPrint);
        default -> throw new RuntimeException("Unknown report format");
      }
    } catch (JRException e) {
      throw new RuntimeException(e);
    }
    return reportContent;
  }
  
	public byte[] exportToXls(JasperPrint jasperPrint) {
		try {
			// Create a ByteArrayOutputStream to hold the Excel data in memory
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

			// Initialize the XLS exporter
			JRXlsExporter exporter = new JRXlsExporter();

			// Set export parameters
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));

			// Export the report to XLS format
			exporter.exportReport();

			log.debug("Report exported to XLS successfully!");

			// Convert the ByteArrayOutputStream to a byte array
			byte[] xlsData = byteArrayOutputStream.toByteArray();

			return xlsData;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
