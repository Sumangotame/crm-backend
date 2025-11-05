package com.suman.crm.service;

import com.suman.crm.model.Opportunity;
import com.suman.crm.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OpportunityService {

    @Autowired
    private OpportunityRepository opportunityRepository;

    public List<Opportunity> getAllOpportunities() {
        return opportunityRepository.findAll();
    }

    public Optional<Opportunity> getOpportunityById(Long id) {
        return opportunityRepository.findById(id);
    }

    public Opportunity createOpportunity(Opportunity opportunity) {
        return opportunityRepository.save(opportunity);
    }

    public Opportunity updateOpportunity(Long id, Opportunity updatedOpportunity) {
        return opportunityRepository.findById(id).map(opp -> {
            opp.setName(updatedOpportunity.getName());
            opp.setStage(updatedOpportunity.getStage());
            opp.setAmount(updatedOpportunity.getAmount());
            opp.setCloseDate(updatedOpportunity.getCloseDate());
            opp.setAccount(updatedOpportunity.getAccount());
            opp.setOwner(updatedOpportunity.getOwner());
            return opportunityRepository.save(opp);
        }).orElseGet(() -> {
            updatedOpportunity.setId(id);
            return opportunityRepository.save(updatedOpportunity);
        });
    }

    public void deleteOpportunity(Long id) {
        opportunityRepository.deleteById(id);
    }
}
