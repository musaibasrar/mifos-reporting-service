package org.ideoholic.mrs.service;

import org.ideoholic.mrs.dao.LoanProductRepository;
import org.ideoholic.mrs.model.LoanProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanProductService {

    @Autowired
    private LoanProductRepository loanProductRepository;

    public List<LoanProduct> getLoanProducts() {
        return loanProductRepository.findAll();
    }
}
