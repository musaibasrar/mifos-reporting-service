package org.ideoholic.mrs.dao;

import org.ideoholic.mrs.model.LoanOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanOfficerRepository extends JpaRepository<LoanOfficer, Long> {
}
