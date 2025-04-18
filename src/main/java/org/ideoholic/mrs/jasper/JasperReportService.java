package org.ideoholic.mrs.jasper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.ideoholic.mrs.dao.ReportParamRelationRepository;
import org.ideoholic.mrs.dao.ReportParamRepository;
import org.ideoholic.mrs.dao.ReportRepository;
import org.ideoholic.mrs.dto.ReportGenParamDto;
import org.ideoholic.mrs.dto.ReportGenerationParamsDto;
import org.ideoholic.mrs.dto.ReportParamDto;
import org.ideoholic.mrs.dto.ReportParamDtoList;
import org.ideoholic.mrs.mappers.ReportParamMapper;
import org.ideoholic.mrs.model.Item;
import org.ideoholic.mrs.model.Report;
import org.ideoholic.mrs.model.ReportParam;
import org.ideoholic.mrs.model.ReportParamRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.poi.export.JRXlsExporter;

@Slf4j
@Service
public class JasperReportService {

	@Autowired
	@Qualifier("reportDataSource")
	private DataSource reportDataSource;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private ReportParamMapper reportParamMapper;

	@Autowired
	private ReportRepository reportRepository;

	@Autowired
	private ReportParamRepository reportParamRepo;

	@Autowired
	private ReportParamRelationRepository reportParamRelationRepo;

	public byte[] getItemReport(List<Item> items, String format) {

		JasperReport jasperReport;

		try {
			jasperReport = (JasperReport) JRLoader.loadObject(ResourceUtils.getFile("item-report.jasper"));
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

	public byte[] generateReport(ReportGenerationParamsDto reportGenerationParamsDto, String reportId,
			String fileFormat) {
		String jasperReportFile = "classpath:reports/";
		Map<String, Object> parameters = new HashMap<>();
		Map<String, String> userParameters = reportGenerationParamsDto.getReportParams().stream()
				.collect(Collectors.toMap(ReportGenParamDto::getParamId, ReportGenParamDto::getParamValue));

		Report report = reportRepository.findByReportId(reportId).orElse(null);
		if (report != null) {
			jasperReportFile += report.getReportFile();
			List<ReportParamRelation> relations = reportParamRelationRepo.findAllByReportId(report.getId());
			for (ReportParamRelation relation : relations) {
				ReportParam reportParam = reportParamRepo.findById(relation.getReportParam().getId()).orElse(null);
				String key = reportParam.getParamFieldName();
				String value = userParameters.get(reportParam.getParamId());
				if (!StringUtils.hasLength(value) && relation.getUseDefault()) {
					value = reportParam.getDefaultValue();
				}
				log.debug("Values sent to report:: key:{}, value:{}", key, value);
				parameters.put(key, value);
			}
			try {
				return generateReportFromFile(jasperReportFile, parameters, fileFormat);
			} catch (JRException | SQLException | IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ReportParamDtoList getAllReportFileParameters(String reportId) {
		Report report = reportRepository.findByReportId(reportId).orElse(null);
		if (report != null) {
			List<ReportParamRelation> relations = reportParamRelationRepo.findAllByReportId(report.getId());
			List<ReportParamDto> reportParams = relations.stream().map(relation -> relation.getReportParam())
					.map(reportParam -> reportParamMapper.mapReportParam(reportParam)).collect(Collectors.toList());

			return ReportParamDtoList.builder().reportParams(reportParams).build();
		}
		return null;
	}

	public ReportParamDtoList getEnabledReportFileParameters(String reportId) {
		Report report = reportRepository.findByReportId(reportId).orElse(null);
		if (report != null) {
			List<ReportParamRelation> relations = reportParamRelationRepo.findAllByReportId(report.getId());
			List<ReportParamDto> reportParams = relations.stream().filter(rel -> rel.getEnabled())
					.map(relation -> relation.getReportParam())
					.map(reportParam -> reportParamMapper.mapReportParam(reportParam)).collect(Collectors.toList());

			return ReportParamDtoList.builder().reportParams(reportParams).build();
		}
		return null;
	}

	private byte[] generateReportFromFile(String jasperFile, Map<String, Object> parameters, String format)
			throws JRException, SQLException, IOException {
		byte[] reportContent = null;
		// Below two lines to load and compile the .jrxml file
		// Resource resource = resourceLoader.getResource(jrxmlPath);
		// JasperReport jasperReport = JasperCompileManager.compileReport(resource.getInputStream());
		// Below two lines to load the .jasper file
		Resource resource = resourceLoader.getResource(jasperFile);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(resource.getInputStream());

		// Fill the report
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
				reportDataSource.getConnection());

		// Export to desired format
		switch (format.toLowerCase()) {
		case "pdf" -> reportContent = JasperExportManager.exportReportToPdf(jasperPrint);
		case "xml" -> reportContent = JasperExportManager.exportReportToXml(jasperPrint).getBytes();
		case "excel" -> reportContent = exportToXls(jasperPrint);
		case "xls" -> reportContent = exportToXls(jasperPrint);
		default -> throw new IllegalArgumentException("Unknown report format:" + format);
		}

		return reportContent;
	}

	private byte[] exportToXls(JasperPrint jasperPrint) {

		try {
			// Create a ByteArrayOutputStream to hold the Excel data in memory
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

			// Initialize the XLS exporter
			JRXlsExporter exporter = new JRXlsExporter();

			// Set export parameters
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));

			SimpleXlsReportConfiguration config = new SimpleXlsReportConfiguration();
			config.setOnePagePerSheet(false);
			config.setDetectCellType(true);
			config.setCollapseRowSpan(false);
			exporter.setConfiguration(config);

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
