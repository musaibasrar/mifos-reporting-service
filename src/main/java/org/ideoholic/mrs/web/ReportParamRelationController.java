package org.ideoholic.mrs.web;

import javax.validation.Valid;

import org.ideoholic.mrs.dto.ReportParamRelationDto;
import org.ideoholic.mrs.dto.ReportParamRelationDtoList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("api/v1/reportParamRelation")
public interface ReportParamRelationController {

	@Operation(summary = "Create report param relation", description = "Creates a Report param relation")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Successfully created the report-param relation"),
			@ApiResponse(responseCode = "400", description = "Bad Request") })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	ResponseEntity<ReportParamRelationDto> createReportParamRelation(@Valid @RequestBody ReportParamRelationDto reportParamRelationDto);

	@PutMapping("/{relationId}")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<ReportParamRelationDto> updateReportParamRelation(@PathVariable("relationId") String relationId,
			@Valid @RequestBody ReportParamRelationDto reportParamRelationDto);

	@GetMapping("/{relationId}")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<ReportParamRelationDto> getReportParamRelation(@PathVariable("relationId") String relationId);

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<ReportParamRelationDtoList> getAllReportParamRelations();

	@DeleteMapping("/{relationId}")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<ReportParamRelationDto> deleteReportParamٌRelation(@PathVariable("relationId") String relationId);
}
