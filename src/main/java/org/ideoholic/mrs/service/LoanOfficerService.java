package org.ideoholic.mrs.service;

import org.ideoholic.mrs.dao.LoanOfficerRepository;
import org.ideoholic.mrs.model.LoanOfficer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanOfficerService {

    @Autowired
    private LoanOfficerRepository loanOfficerRepository;

    public List<LoanOfficer> getAllLoanOfficers() {
        return loanOfficerRepository.findAll();
    }
}
