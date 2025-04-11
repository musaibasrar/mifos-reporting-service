package org.ideoholic.mrs.web;

import javax.validation.Valid;

import org.ideoholic.mrs.dto.ReportParamDto;
import org.ideoholic.mrs.dto.ReportParamDtoList;
import org.ideoholic.mrs.dto.ReportParamDto;
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

@RequestMapping("api/v1/reportParam")
public interface ReportParamController {

	@Operation(summary = "Create report param", description = "Creates a Report param that contains values that can be used ")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Successfully created the report file"),
			@ApiResponse(responseCode = "400", description = "Bad Request") })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	ResponseEntity<ReportParamDto> createReportParam(@Valid @RequestBody ReportParamDto reportParamDto);

	@PutMapping("/{paramId}")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<ReportParamDto> updateReportParam(@PathVariable("paramId") String paramId,
			@Valid @RequestBody ReportParamDto ReportParamDto);

	@GetMapping("/{paramId}")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<ReportParamDto> getReportParam(@PathVariable("paramId") String paramId);

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<ReportParamDtoList> getAllReportParamss();

	@DeleteMapping("/{paramId}")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<ReportParamDto> deleteReportParam(@PathVariable("paramId") String paramId);
}
