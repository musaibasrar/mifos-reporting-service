package org.ideoholic.mrs.web;

import java.io.IOException;
import java.util.Optional;

import org.ideoholic.mrs.dao.ItemRepository;
import org.ideoholic.mrs.dto.ReportGenerationParamsDto;
import org.ideoholic.mrs.dto.ReportParamDtoList;
import org.ideoholic.mrs.jasper.JasperReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.sf.jasperreports.engine.JRException;

@RestController
public class ReportControllerImpl implements ReportController {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private JasperReportService jasperReportService;

	@GetMapping("item-report/{format}")
	public ResponseEntity<Resource> getItemReport(@PathVariable String format) throws JRException, IOException {

		byte[] reportContent = jasperReportService.getItemReport(itemRepository.findAll(), format);

		ByteArrayResource resource = new ByteArrayResource(reportContent);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.contentLength(resource.contentLength())
				.header(HttpHeaders.CONTENT_DISPOSITION,
						ContentDisposition.attachment().filename("item-report." + format).build().toString())
				.body(resource);
	}

	@Override
	public ResponseEntity<ReportParamDtoList> getAllReportFileParameters(String reportId) {
		ReportParamDtoList result = jasperReportService.getAllReportFileParameters(reportId);
		if (result == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Report Not Found:" + reportId);
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@Override
	public ResponseEntity<ReportParamDtoList> getEnabledReportFileParameters(String reportId) {
		ReportParamDtoList result = jasperReportService.getEnabledReportFileParameters(reportId);
		if (result == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Report Not Found:" + reportId);
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@Override
	public ResponseEntity<Resource> generateReport(ReportGenerationParamsDto reportGenerationParamsDto, String reportId,
			Optional<String> format) {

		String fileName = StringUtils.hasLength(reportGenerationParamsDto.getFileName())
				? reportGenerationParamsDto.getFileName()
				: "report";
		String fileFormat = format.orElse("pdf");

		byte[] reportContent = jasperReportService.generateReport(reportGenerationParamsDto, reportId, fileFormat);
		if (reportContent == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Unable to create the Report with Report ID:" + reportId);
		}

		ByteArrayResource resource = new ByteArrayResource(reportContent);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.contentLength(resource.contentLength())
				.header(HttpHeaders.CONTENT_DISPOSITION,
						ContentDisposition.attachment().filename(fileName + "." + fileFormat).build().toString())
				.body(resource);
	}

}
