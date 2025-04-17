package org.ideoholic.mrs.dao;

import java.util.List;
import java.util.Optional;

import org.ideoholic.mrs.model.ReportParamRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportParamRelationRepository extends JpaRepository<ReportParamRelation, Long> {

	Optional<ReportParamRelation> findByRelationId(String relationId);
	
	@Query("FROM ReportParamRelation WHERE report.id = :reportId")
	List<ReportParamRelation> findAllByReportId(@Param("reportId")Long reportId);
	
	@Query("FROM ReportParamRelation WHERE reportParam.id = :reportParam")
	List<ReportParamRelation> findAllByReportParamId(@Param("reportParam")Long reportParam);
	
	@Query("FROM ReportParamRelation WHERE report.id = :reportId AND reportParam.id = :reportParam")
	ReportParamRelation findByReportIdAndReportParamId(@Param("reportId")Long reportId, @Param("reportParam")Long reportParam);
}
