package com.sap.NotesRestApplication.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.management.InstanceNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
public class NotesController {

    @Autowired
    private NotesService notesService;

    @RequestMapping("/notes")
    public Iterable<Note> getAllNotes(){
        return notesService.getAllNotes();
    }

    @RequestMapping("notes/{id}")
    public Note getNote(@PathVariable int id){
        if(notesService.getNote(id).isPresent()) {
            return notesService.getNote(id).get();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/notes")
    public Note addNote(@Valid @RequestBody Note note){
        try{
            return notesService.addNote(note.getAuthor(),note.getText());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ITEM NOT ADDED",e);
        }

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/notes/{id}")
    public Note changeNote(@PathVariable int id, @RequestBody String text){
        if(notesService.changeNote(id,text) != null) {
            return notesService.changeNote(id,text);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND NOTE WITH THAT ID");
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "notes/{id}")
    public void removeNote(@PathVariable int id){
        try {
             notesService.removeNote(id);
        } catch (InstanceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND NOTE WITH THAT ID");
        }
        throw new ResponseStatusException(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "notes/deleteAll")
    public void removeAllNotes(){
        try{
            notesService.removeAllNotes();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UNSUCCESSFULLY DELETING");
        }
        throw new ResponseStatusException(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "notes/author/{author}")
    public Iterable<Note> findAllByAuthor(@PathVariable @Valid String author){
        try {
            return notesService.findAllByAuthor(author);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
