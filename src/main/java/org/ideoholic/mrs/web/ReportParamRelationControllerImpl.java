package org.ideoholic.mrs.web;

import javax.validation.Valid;

import org.ideoholic.mrs.dto.ReportParamRelationDto;
import org.ideoholic.mrs.dto.ReportParamRelationDtoList;
import org.ideoholic.mrs.service.ReportParamRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportParamRelationControllerImpl implements ReportParamRelationController {
	
	@Autowired
	private ReportParamRelationService reportParamRelationService;

	@Override
	public ResponseEntity<ReportParamRelationDto> createReportParamRelation(
			@Valid ReportParamRelationDto reportParamRelationDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(reportParamRelationService.createReportParamRelation(reportParamRelationDto));
	}

	@Override
	public ResponseEntity<ReportParamRelationDto> updateReportParamRelation(String relationId,
			@Valid ReportParamRelationDto reportParamRelationDto) {
		return ResponseEntity.status(HttpStatus.OK).body(reportParamRelationService.updateReportParamRelation(relationId, reportParamRelationDto));
	}

	@Override
	public ResponseEntity<ReportParamRelationDto> getReportParamRelation(String relationId) {
		return ResponseEntity.ok(reportParamRelationService.getReportParamRelation(relationId));
	}

	@Override
	public ResponseEntity<ReportParamRelationDtoList> getAllReportParamRelations() {
		return ResponseEntity.ok(reportParamRelationService.getAllReportParamRelations());
	}

	@Override
	public ResponseEntity<ReportParamRelationDto> deleteReportParamٌRelation(String relationId) {
		return ResponseEntity.ok(reportParamRelationService.deleteReportParamRelation(relationId));
	}

}
