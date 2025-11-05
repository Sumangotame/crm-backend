package com.suman.crm.repository;

import com.suman.crm.model.Contact;
import com.suman.crm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByOwner(User owner);
}
