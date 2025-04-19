package org.ideoholic.mrs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.ideoholic.mrs.dao.ReportRepository;
import org.ideoholic.mrs.dto.ReportDto;
import org.ideoholic.mrs.dto.ReportDtoList;
import org.ideoholic.mrs.mappers.ReportMapper;
import org.ideoholic.mrs.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReportFileService {

	@Autowired
	private ReportRepository reportRepository;

	@Autowired
	private ReportMapper reportMapper;

	public ReportDto createReport(ReportDto reportDto) {
		Report report = reportMapper.mapReportDto(reportDto);
		reportRepository.save(report);
		return reportMapper.mapReport(report);
	}
	
	public ReportDto getReport(String reportId) {
		Report report = reportRepository.findByReportId(reportId).orElseThrow();
		return reportMapper.mapReport(report);
	}
	
	public ReportDtoList getAllReports() {
		List<Report> reportList = reportRepository.findAll();
		
		List<ReportDto> reportsDto = reportList.stream()
				.map(reportMapper::mapReport)
				.collect(Collectors.toList());
		
		return ReportDtoList.builder()
				.reports(reportsDto)
				.build();
	}
	
	public ReportDto updateReport(String reportId, ReportDto reportDto) {
		Report report = reportRepository.findByReportId(reportId).orElseThrow();
		reportMapper.updateReport(report, reportDto);
		reportRepository.save(report);
		return reportMapper.mapReport(report);
	}
	
	public ReportDto deleteReport(String reportId) {
		Report report = reportRepository.findByReportId(reportId).orElseThrow();
		reportRepository.deleteById(report.getId());
		return reportMapper.mapReport(report);
	}

}
