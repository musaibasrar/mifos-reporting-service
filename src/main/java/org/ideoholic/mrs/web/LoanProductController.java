package org.ideoholic.mrs.web;

import org.ideoholic.mrs.model.LoanProduct;
import org.ideoholic.mrs.service.LoanProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan-products")
public class LoanProductController {

    @Autowired
    private LoanProductService loanProductService;

    @GetMapping(produces = "application/json")
    public List<LoanProduct> getLoanProducts() {
        return loanProductService.getLoanProducts();
    }
}
