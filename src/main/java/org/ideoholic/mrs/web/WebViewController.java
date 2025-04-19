package org.ideoholic.mrs.web;

import org.ideoholic.mrs.jasper.JasperReportService;
import org.ideoholic.mrs.service.ReportFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebViewController {
    
    @Autowired
    private ReportFileService reportFileService;
    
    @Autowired
    private JasperReportService jasperReportService;

    @GetMapping("/mrs")
    public String index(Model model) {
        model.addAttribute("reports", reportFileService.getAllReports().getReports());
        return "home";
    }

    @GetMapping("/report/{reportId}")
    public String reportForm(@PathVariable String reportId, Model model) {
        model.addAttribute("report", reportFileService.getReport(reportId));
        model.addAttribute("params", jasperReportService.getEnabledReportFileParameters(reportId).getReportParams());
        return "report-form";
    }
} 