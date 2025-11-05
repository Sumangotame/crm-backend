package com.suman.crm.service;

import com.suman.crm.model.Contact;
import com.suman.crm.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact updateContact(Long id, Contact updatedContact) {
        return contactRepository.findById(id).map(contact -> {
            contact.setFirstName(updatedContact.getFirstName());
            contact.setLastName(updatedContact.getLastName());
            contact.setEmail(updatedContact.getEmail());
            contact.setPhone(updatedContact.getPhone());
            contact.setLead(updatedContact.getLead());
            contact.setAccount(updatedContact.getAccount());
            return contactRepository.save(contact);
        }).orElseGet(() -> {
            updatedContact.setId(id);
            return contactRepository.save(updatedContact);
        });
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
