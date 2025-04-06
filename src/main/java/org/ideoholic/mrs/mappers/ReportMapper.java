package org.ideoholic.mrs.mappers;

import org.ideoholic.mrs.dto.ReportDto;
import org.ideoholic.mrs.model.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReportMapper {
	ReportDto mapReport(Report report);

	@Mapping(target = "reportId", ignore = true)
	Report mapReportDto(ReportDto reportDto);
	
	@Mapping(target = "reportId", ignore = true)
	void updateReport(@MappingTarget Report report, ReportDto reportDto);
}
