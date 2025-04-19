package org.ideoholic.mrs.web;

import org.ideoholic.mrs.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebViewController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/mrs")
    public String index(Model model) {
        model.addAttribute("reports", reportService.getAllReports().getReports());
        return "home";
    }
  
} 