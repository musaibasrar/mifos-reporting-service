package org.ideoholic.mrs.dao;

import org.ideoholic.mrs.model.ReportParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportParamRepository extends JpaRepository<ReportParam, Long> {

}
