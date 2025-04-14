package org.ideoholic.mrs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportParamRelationDto {
	
	private String relationId;

	private String reportId;

	private String paramId;

	private Boolean enabled;

	private Boolean useDefault;

}