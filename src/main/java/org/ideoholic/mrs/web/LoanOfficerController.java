package org.ideoholic.mrs.web;

import org.ideoholic.mrs.model.LoanOfficer;
import org.ideoholic.mrs.service.LoanOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/loan-officers")
public class LoanOfficerController {

    @Autowired
    private LoanOfficerService loanOfficerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<LoanOfficer>> getLoanOfficers() {
        List<LoanOfficer> loanOfficers = loanOfficerService.getAllLoanOfficers();
        Map<String, List<LoanOfficer>> response = new HashMap<>();
        response.put("list", loanOfficers);
        return response;
    }
}
