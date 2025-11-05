package com.suman.crm.controller;

import com.suman.crm.model.Opportunity;
import com.suman.crm.service.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/opportunities")
public class OpportunityController {

    @Autowired
    private OpportunityService opportunityService;

    @GetMapping
    public List<Opportunity> getAllOpportunities() {
        return opportunityService.getAllOpportunities();
    }

    @GetMapping("/{id}")
    public Optional<Opportunity> getOpportunityById(@PathVariable Long id) {
        return opportunityService.getOpportunityById(id);
    }

    @PostMapping
    public Opportunity createOpportunity(@RequestBody Opportunity opportunity) {
        return opportunityService.createOpportunity(opportunity);
    }

    @PutMapping("/{id}")
    public Opportunity updateOpportunity(@PathVariable Long id, @RequestBody Opportunity opportunity) {
        return opportunityService.updateOpportunity(id, opportunity);
    }

    @DeleteMapping("/{id}")
    public void deleteOpportunity(@PathVariable Long id) {
        opportunityService.deleteOpportunity(id);
    }
}

