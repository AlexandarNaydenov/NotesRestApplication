package com.sap.NotesRestApplication.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.management.InstanceNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class NotesController {

    @Autowired
    private NotesService notesService;

    @RequestMapping("/notes")
    public Iterable<Note> getAllNotes() {
        return notesService.getAllNotes();
    }

    @RequestMapping("notes/{id}")
    public Note getNote(@PathVariable int id) {
        if (notesService.getNote(id).isPresent()) {
            return notesService.getNote(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/notes")
    public Note addNote(@Valid @RequestBody Note note) {
        return notesService.addNote(note.getAuthor(), note.getText());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/notes/{id}")
    public Note changeNote(@PathVariable int id, @RequestBody String text) {
        if (notesService.changeNote(id, text) != null) {
            return notesService.changeNote(id, text);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND NOTE WITH THAT ID");
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "notes/{id}")
    public Note removeNote(@PathVariable int id) {
        Optional<Note> deletedNote = notesService.deleteNote(id);
        if (deletedNote.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND NOTE WITH THAT ID");
        }
        return deletedNote.get();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "notes/deleteAll")
    public void removeAllNotes() {
        notesService.removeAllNotes();
        throw new ResponseStatusException(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "notes/author/{author}")
    public Iterable<Note> findAllByAuthor(@PathVariable @Valid String author) {
        return notesService.findAllByAuthor(author);
    }
}
