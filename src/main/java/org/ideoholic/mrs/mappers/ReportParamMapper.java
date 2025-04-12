package org.ideoholic.mrs.mappers;

import org.ideoholic.mrs.dto.ReportParamDto;
import org.ideoholic.mrs.model.ParameterType;
import org.ideoholic.mrs.model.ReportParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, imports = ParameterType.class)
public interface ReportParamMapper {
	@Mapping(target = "paramType", expression = "java(reportParam.getParamType().getValue())")
	ReportParamDto mapReportParam(ReportParam reportParam);

	@Mapping(target = "paramId", ignore = true)
	@Mapping(target = "paramType", expression = "java(ParameterType.fromValue(reportParamDto.getParamType()))")
	ReportParam mapReportParamDto(ReportParamDto reportParamDto);
	
	@Mapping(target = "paramId", ignore = true)
	@Mapping(target = "paramType", expression = "java(ParameterType.fromValue(reportParamDto.getParamType()))")
	void updateReportParam(@MappingTarget ReportParam reportParam, ReportParamDto reportParamDto);
}
