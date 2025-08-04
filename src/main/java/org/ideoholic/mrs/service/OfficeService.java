package org.ideoholic.mrs.service;

import org.ideoholic.mrs.dao.OfficeRepository;
import org.ideoholic.mrs.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    public List<Office> getOffices() {
        return officeRepository.findAll();
    }
}
