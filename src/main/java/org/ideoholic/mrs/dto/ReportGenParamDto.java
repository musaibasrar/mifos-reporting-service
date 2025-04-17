package org.ideoholic.mrs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportGenParamDto {
	private String paramId;
	private String paramValue;
}
