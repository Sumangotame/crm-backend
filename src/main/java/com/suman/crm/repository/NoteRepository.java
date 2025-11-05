package com.suman.crm.repository;

import com.suman.crm.model.Note;
import com.suman.crm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByOwner(User user);
}
