package org.ideoholic.mrs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.ideoholic.mrs.dao.ReportParamRelationRepository;
import org.ideoholic.mrs.dao.ReportParamRepository;
import org.ideoholic.mrs.dao.ReportRepository;
import org.ideoholic.mrs.dto.ReportParamRelationDto;
import org.ideoholic.mrs.dto.ReportParamRelationDtoList;
import org.ideoholic.mrs.mappers.ReportParamRelationMapper;
import org.ideoholic.mrs.model.ReportParamRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReportParamRelationService {

	@Autowired
	private ReportRepository reportRepo;

	@Autowired
	private ReportParamRepository reportParamRepo;

	@Autowired
	private ReportParamRelationRepository reportParamRelationRepo;

	@Autowired
	private ReportParamRelationMapper reportParamRelationMapper;

	public ReportParamRelationDto createReportParamRelation(ReportParamRelationDto reportParamDto) {
		ReportParamRelation reportParamRelation = reportParamRelationMapper.mapReportParamRelationDto(reportParamDto);
		reportParamRelation.setReport(reportRepo.findByReportId(reportParamDto.getReportId()).orElseThrow());
		reportParamRelation.setReportParam(reportParamRepo.findByParamId(reportParamDto.getParamId()).orElseThrow());

		reportParamRelationRepo.save(reportParamRelation);

		return reportParamRelationMapper.mapReportParamRelation(reportParamRelation);
	}

	public ReportParamRelationDto getReportParamRelation(String relationId) {
		ReportParamRelation reportParamRelation = reportParamRelationRepo.findByRelationId(relationId).orElseThrow();
		return reportParamRelationMapper.mapReportParamRelation(reportParamRelation);
	}

	public ReportParamRelationDtoList getAllReportParamRelations() {
		List<ReportParamRelation> reportParamRelationList = reportParamRelationRepo.findAll();

		List<ReportParamRelationDto> reportParamRelations = reportParamRelationList.stream()
				.map(reportParamRelation -> reportParamRelationMapper.mapReportParamRelation(reportParamRelation))
				.collect(Collectors.toList());

		return ReportParamRelationDtoList.builder().reportParamRelations(reportParamRelations).build();
	}

	public ReportParamRelationDto updateReportParamRelation(String relationId,
			ReportParamRelationDto reportParamRelationDto) {
		ReportParamRelation reportParamRelation = reportParamRelationRepo.findByRelationId(relationId).orElseThrow();
		reportParamRelationMapper.updateReportParamRelation(reportParamRelation, reportParamRelationDto);
		return reportParamRelationMapper.mapReportParamRelation(reportParamRelation);
	}

	public ReportParamRelationDto deleteReportParamRelation(String relationId) {
		ReportParamRelation reportParamRelation = reportParamRelationRepo.findByRelationId(relationId).orElseThrow();

		reportParamRelationRepo.deleteById(reportParamRelation.getId());

		return reportParamRelationMapper.mapReportParamRelation(reportParamRelation);
	}

}
