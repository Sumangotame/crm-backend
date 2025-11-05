package com.suman.crm.controller;

import com.suman.crm.model.Lead;
import com.suman.crm.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @GetMapping
    public List<Lead> getAllLeads() {
        return leadService.getAllLeads();
    }

    @GetMapping("/{id}")
    public Optional<Lead> getLeadById(@PathVariable Long id) {
        return leadService.getLeadById(id);
    }

    @PostMapping
    public Lead createLead(@RequestBody Lead lead) {
        return leadService.createLead(lead);
    }

    @PutMapping("/{id}")
    public Lead updateLead(@PathVariable Long id, @RequestBody Lead lead) {
        return leadService.updateLead(id, lead);
    }

    @DeleteMapping("/{id}")
    public void deleteLead(@PathVariable Long id) {
        leadService.deleteLead(id);
    }

   @GetMapping("/status-count")
    public ResponseEntity<Map<String, Long>> getLeadsByStatus() {
        Map<String, Long> statusCount = leadService.getLeadsByStatus();
        return ResponseEntity.ok(statusCount);
    }

}

