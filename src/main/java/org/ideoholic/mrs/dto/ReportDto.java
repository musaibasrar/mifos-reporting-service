package org.ideoholic.mrs.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {

	private Long reportId;

	@NotNull(message = "Report Name cannot be null")
	@NotEmpty(message = "Report Name cannot be empty")
	private String reportName;

	@NotNull(message = "Report File Name cannot be null")
	@NotEmpty(message = "Report File Name cannot be empty")
	@Size(min = 1, max = 1000, message = "Report File Name must be between 1 and 1000 characters")
	private String reportFile;

}
