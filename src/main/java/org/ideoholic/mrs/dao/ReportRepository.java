package org.ideoholic.mrs.dao;

import java.util.Optional;

import org.ideoholic.mrs.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
	
	Optional<Report> findByReportId(String reportId);
}
