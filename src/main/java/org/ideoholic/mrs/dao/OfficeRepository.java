package org.ideoholic.mrs.dao;

import org.ideoholic.mrs.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
    // findAll() comes built-in
}
