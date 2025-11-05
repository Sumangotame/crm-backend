package com.suman.crm.service;

import com.suman.crm.model.Note;
import com.suman.crm.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public Note updateNote(Long id, Note updatedNote) {
        return noteRepository.findById(id).map(note -> {
            note.setContent(updatedNote.getContent());
            note.setOwner(updatedNote.getOwner());
            return noteRepository.save(note);
        }).orElseGet(() -> {
            updatedNote.setId(id);
            return noteRepository.save(updatedNote);
        });
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
