package org.ideoholic.mrs.web;

import java.util.Optional;

import org.ideoholic.mrs.dto.ReportGenerationParamsDto;
import org.ideoholic.mrs.dto.ReportParamDtoList;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("api/v1/report")
public interface ReportController {

	@Operation(summary = "Get all report params", description = "Given a report-id, fetch all the parameters of that report")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Report parameters list for the given report-id"),
			@ApiResponse(responseCode = "400", description = "Bad Request - If report does not exist") })
	@GetMapping("/{reportId}/all")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<ReportParamDtoList> getAllReportFileParameters(@PathVariable("reportId") String reportId);
	
	@Operation(summary = "Get enabled report params", description = "Given a report-id, fetch all the parameters of that report that are enabled")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Report parameters list for the given report-id"),
			@ApiResponse(responseCode = "400", description = "Bad Request - If report does not exist") })
	@GetMapping("/{reportId}")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<ReportParamDtoList> getEnabledReportFileParameters(@PathVariable("reportId") String reportId);

	@Operation(summary = "Generate report", description = "Given a report-id and the required parameters, generate the report in given format")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "The generated report is returned"),
			@ApiResponse(responseCode = "400", description = "Bad Request - if report does not exist"),
			@ApiResponse(responseCode = "500", description = "Internal Error - if report generation fails") })
	@PostMapping("/{reportId}")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<Resource> generateReport(@RequestBody ReportGenerationParamsDto reportGenerationParamsDto, @PathVariable("reportId") String reportId,
			@RequestParam(name = "format") Optional<String> format);

}
