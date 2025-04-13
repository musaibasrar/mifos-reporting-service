package org.ideoholic.mrs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.ideoholic.mrs.dao.ReportParamRepository;
import org.ideoholic.mrs.dto.ReportParamDto;
import org.ideoholic.mrs.dto.ReportParamDtoList;
import org.ideoholic.mrs.mappers.ReportParamMapper;
import org.ideoholic.mrs.model.ReportParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReportParamService {

	@Autowired
	private ReportParamRepository reportParamRepo;

	@Autowired
	private ReportParamMapper reportParamMapper;

	public ReportParamDto createReportParam(ReportParamDto reportParamDto) {
		ReportParam reportParam = reportParamMapper.mapReportParamDto(reportParamDto);
		reportParamRepo.save(reportParam);
		return reportParamMapper.mapReportParam(reportParam);
	}

	public ReportParamDto getReportParam(String paramId) {
		ReportParam reportParam = reportParamRepo.findByParamId(paramId).orElseThrow();
		return reportParamMapper.mapReportParam(reportParam);
	}

	public ReportParamDtoList getAllReportParams() {
		List<ReportParam> reportParamList = reportParamRepo.findAll();

		List<ReportParamDto> reportsDto = reportParamList.stream()
				.map(reportParam -> reportParamMapper.mapReportParam(reportParam)) // Mapping to ReportDto
				.collect(Collectors.toList());// Collecting the results into a list

		return ReportParamDtoList.builder().reportParams(reportsDto).build();
	}

	public ReportParamDto updateReportParam(String reportParamId, ReportParamDto reportParamDto) {
		// First fetch to ensure that the report exists
		ReportParam reportParam = reportParamRepo.findByParamId(reportParamId).orElseThrow();

		reportParamMapper.updateReportParam(reportParam, reportParamDto);

		reportParamRepo.save(reportParam);

		return reportParamMapper.mapReportParam(reportParam);
	}

	public ReportParamDto deleteReportParam(String reportParamId) {
		ReportParam reportParam = reportParamRepo.findByParamId(reportParamId).orElseThrow();

		reportParamRepo.deleteById(reportParam.getId());

		return reportParamMapper.mapReportParam(reportParam);
	}

}
