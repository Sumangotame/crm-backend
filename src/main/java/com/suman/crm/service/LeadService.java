package com.suman.crm.service;

import com.suman.crm.model.Lead;
import com.suman.crm.model.User;
import com.suman.crm.repository.LeadRepository;
import com.suman.crm.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    public Optional<Lead> getLeadById(Long id) {
        return leadRepository.findById(id);
    }

    public Lead createLead(Lead lead) {
    return leadRepository.save(lead);
    }

    public Lead updateLead(Long id, Lead updatedLead) {
            String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
             User currentUser = userRepository.findByUsername(currentUsername)
        .orElseThrow(() -> new RuntimeException("User not found"));
        return leadRepository.findById(id).map(lead -> {
            lead.setFirstName(updatedLead.getFirstName());
            lead.setLastName(updatedLead.getLastName());
            lead.setEmail(updatedLead.getEmail());
            lead.setPhone(updatedLead.getPhone());
            lead.setCompany(updatedLead.getCompany());
            lead.setStatus(updatedLead.getStatus());
            lead.setSource(updatedLead.getSource());
            lead.setUpdatedBy(currentUser);
            return leadRepository.save(lead);
        }).orElseGet(() -> {
            updatedLead.setId(id);
            return leadRepository.save(updatedLead);
        });
    }

    public void deleteLead(Long id) {
        leadRepository.deleteById(id);
    }

   public Map<String, Long> getLeadsByStatus() {
        // Convert enum to String using .name()
        return leadRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        lead -> lead.getStatus().name(),
                        Collectors.counting()
                ));
    }
}
