package org.ideoholic.mrs.mappers;

import org.ideoholic.mrs.dto.ReportParamRelationDto;
import org.ideoholic.mrs.model.ParameterType;
import org.ideoholic.mrs.model.ReportParamRelation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, imports = ParameterType.class)
public interface ReportParamRelationMapper {
	
	@Mapping(target = "reportId", source = "report.reportId")
	@Mapping(target = "paramId", source = "reportParam.paramId")
	ReportParamRelationDto mapReportParamRelation(ReportParamRelation reportParamRelation);

	@Mapping(target = "relationId", ignore = true)
	ReportParamRelation mapReportParamRelationDto(ReportParamRelationDto reportParamRelationDto);
	
	@Mapping(target = "relationId", ignore = true)
	void updateReportParamRelation(@MappingTarget ReportParamRelation reportParamRelation, ReportParamRelationDto reportParamRelationDto);
}
