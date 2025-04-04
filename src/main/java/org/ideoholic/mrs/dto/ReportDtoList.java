package org.ideoholic.mrs.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportDtoList {
	private List<ReportDto> reports;
}
