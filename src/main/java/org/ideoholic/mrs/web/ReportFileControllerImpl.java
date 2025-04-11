package org.ideoholic.mrs.web;

import org.ideoholic.mrs.dto.ReportDto;
import org.ideoholic.mrs.dto.ReportDtoList;
import org.ideoholic.mrs.service.ReportFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportFileControllerImpl implements ReportFileController {

	@Autowired
	private ReportFileService reportFileService;

	@Override
	public ResponseEntity<ReportDto> createReportFile(ReportDto reportDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(reportFileService.createReport(reportDto));
	}

	@Override
	public ResponseEntity<ReportDto> updateReportFile(String reportId, ReportDto reportDto) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(reportFileService.updateReport(Long.parseLong(reportId), reportDto));
	}

	@Override
	public ResponseEntity<ReportDto> getReportFile(String reportId) {
		return ResponseEntity.ok(reportFileService.getReport(Long.parseLong(reportId)));
	}

	@Override
	public ResponseEntity<ReportDtoList> getAllReportFiles() {
		return ResponseEntity.ok(reportFileService.getAllReports());
	}

	@Override
	public ResponseEntity<ReportDto> deleteReportFile(String reportId) {
		return ResponseEntity.ok(reportFileService.deleteReport(Long.parseLong(reportId)));
	}

}
