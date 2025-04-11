package org.ideoholic.mrs.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportParamDto {

	private Long paramId;

	@NotNull(message = "Report Param Name cannot be null")
	@NotEmpty(message = "Report Param Name cannot be empty")
	private String paramName;

	private String paramFieldName;

	@Schema(description = "Supported values are: string, integer, float date and boolean")
	private String paramType;

	private String defaultValue;

}