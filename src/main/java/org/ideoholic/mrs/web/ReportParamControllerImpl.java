package org.ideoholic.mrs.web;

import javax.validation.Valid;

import org.ideoholic.mrs.dto.ReportParamDto;
import org.ideoholic.mrs.dto.ReportParamDtoList;
import org.ideoholic.mrs.service.ReportParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportParamControllerImpl implements ReportParamController {

	@Autowired
	private ReportParamService reportParamService;

	@Override
	public ResponseEntity<ReportParamDto> createReportParam(ReportParamDto reportParamDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(reportParamService.createReportParam(reportParamDto));
	}

	@Override
	public ResponseEntity<ReportParamDto> updateReportParam(String paramId, @Valid ReportParamDto ReportParamDto) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(reportParamService.updateReportParam(Long.parseLong(paramId), ReportParamDto));
	}

	@Override
	public ResponseEntity<ReportParamDto> getReportParam(String paramId) {
		return ResponseEntity.ok(reportParamService.getReportParam(Long.parseLong(paramId)));
	}

	@Override
	public ResponseEntity<ReportParamDtoList> getAllReportParamss() {
		return ResponseEntity.ok(reportParamService.getAllReportParams());
	}

	@Override
	public ResponseEntity<ReportParamDto> deleteReportParam(String paramId) {
		return ResponseEntity.ok(reportParamService.deleteReportParam(Long.parseLong(paramId)));
	}

}
