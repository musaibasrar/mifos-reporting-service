package org.ideoholic.mrs.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportGenerationParamsDto {

	private String fileName;
	private List<ReportGenParamDto> reportParams;

}
