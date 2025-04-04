package org.ideoholic.mrs.jasper;

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
	
	public ReportDto getReport(Long reportId) {
		Report report = reportRepository.findById(reportId).orElseThrow();
		return reportMapper.mapReport(report);
	}
	
	public ReportDtoList getAllReports() {
		List<Report> reportList = reportRepository.findAll();
		
		List<ReportDto> reportsDto = reportList.stream()
				.map(report -> reportMapper.mapReport(report)) // Mapping to ReportDto
				.collect(Collectors.toList());// Collecting the results into a list
		
		return ReportDtoList.builder()
				.reports(reportsDto)
				.build();
	}
	
	public ReportDto updateReport(Long reportId, ReportDto reportDto) {
		// First fetch to ensure that the report exists
		Report report = reportRepository.findById(reportId).orElseThrow();
		
		reportMapper.updateReport(report, reportDto);
		
		reportRepository.save(report);
		
		return reportMapper.mapReport(report);
	}
	
	public ReportDto deleteReport(Long reportId) {
		Report report = reportRepository.findById(reportId).orElseThrow();
		
		reportRepository.deleteById(reportId);
		
		return reportMapper.mapReport(report);
	}

}
