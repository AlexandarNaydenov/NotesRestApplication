package com.sap.NotesRestApplication.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.*;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    public NotesService() {
    }

    public Iterable<Note> getAllNotes() {
        return notesRepository.findAll();
    }

    public Optional<Note> getNote(int id) {
        return notesRepository.findById(id);
    }

    public Note addNote(String author, String text) {
        return notesRepository.save(new Note(author, text));
    }

    public Note changeNote(int id, String newText) {
        if (notesRepository.findById(id).isEmpty()) {
            return null;
        }
        Note temp = notesRepository.findById(id).get();
        temp.setText(newText);
        return notesRepository.save(temp);

    }

    public void removeNote(int id) throws InstanceNotFoundException {
        if (notesRepository.findById(id).isEmpty()) {
            throw new InstanceNotFoundException();
        }
            notesRepository.deleteById(id);
    }

    public void removeAllNotes() {
        notesRepository.deleteAll();
    }

    public Iterable<Note> findAllByAuthor(String author) {
        return notesRepository.findAllByAuthor(author);
    }
}
