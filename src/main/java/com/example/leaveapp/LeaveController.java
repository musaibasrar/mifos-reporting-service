
package com.example.leaveapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LeaveController {

    @Autowired
    private LeaveRequestRepository repo;

    @GetMapping("/leave")
    public String showLeaveForm(Model model) {
        model.addAttribute("leave", new LeaveForm());
        return "leave-form";
    }

    @PostMapping("/leave")
    public String submitLeave(@ModelAttribute LeaveForm leave, Model model) {
        LeaveRequest req = new LeaveRequest();
        req.setEmployeeName(leave.getEmployeeName());
        req.setLeaveType(leave.getLeaveType());
        req.setFromDate(leave.getFromDate());
        req.setToDate(leave.getToDate());
        repo.save(req);

        model.addAttribute("message", "Leave application submitted and saved!");
        model.addAttribute("leave", leave);
        return "leave-success";
    }
}
