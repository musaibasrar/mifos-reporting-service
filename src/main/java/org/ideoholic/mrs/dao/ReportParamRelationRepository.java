package org.ideoholic.mrs.dao;

import java.util.Optional;

import org.ideoholic.mrs.model.ReportParamRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportParamRelationRepository extends JpaRepository<ReportParamRelation, Long> {

	Optional<ReportParamRelation> findByRelationId(String relationId);
}
