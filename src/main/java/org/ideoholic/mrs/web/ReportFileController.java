package org.ideoholic.mrs.web;

import javax.validation.Valid;

import org.ideoholic.mrs.dto.ReportDto;
import org.ideoholic.mrs.dto.ReportDtoList;
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

@RequestMapping("api/v1/reportFile")
public interface ReportFileController {

    @Operation(summary = "Create report", description = "Creates a Report that maps a user-friendly report file name to the Jasper report file")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created the report file"),
        @ApiResponse(responseCode = "400", description = "Bad Request")
    })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<ReportDto> createReportFile(@Valid @RequestBody ReportDto reportDto);
    
	@PutMapping("/{reportId}")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<ReportDto> updateReportFile(@PathVariable("reportId") String reportId, @Valid @RequestBody ReportDto reportDto);
	
	@GetMapping("/{reportId}")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<ReportDto> getReportFile(@PathVariable("reportId") String reportId);

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<ReportDtoList> getAllReportFiles();
	
	@DeleteMapping("/{reportId}")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<ReportDto> deleteReportFile(@PathVariable("reportId") String reportId);
}
