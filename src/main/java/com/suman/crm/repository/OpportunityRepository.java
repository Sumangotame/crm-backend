package com.suman.crm.repository;

import com.suman.crm.model.Opportunity;
import com.suman.crm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
    List<Opportunity> findByOwner(User owner);
}

