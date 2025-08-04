package org.ideoholic.mrs.web;

import org.ideoholic.mrs.model.Office;
import org.ideoholic.mrs.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offices")
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Office>> getAllOffices() {
        return ResponseEntity.ok(officeService.getOffices());
    }
}
