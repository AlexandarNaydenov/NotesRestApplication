package com.sap.NotesRestApplication.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.management.InstanceNotFoundException;
import java.util.List;

@RestController
public class NotesController {

    @Autowired
    private NotesService notesService;

    @RequestMapping("/notes")
    public List<Note> getAllNotes(){
        return notesService.getAllNotes();
    }

    @RequestMapping("notes/{id}")
    public Note getNote(@PathVariable int id){
        try {
            return notesService.getNote(id);
        } catch (InstanceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/notes")
    public void addNote(@RequestBody Note note){
        try{
        notesService.addNote(note.getAuthor(),note.getText());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ITEM NOT ADDED",e);
        }
        throw new ResponseStatusException(HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/notes/{id}")
    public void changeNote(@PathVariable int id, @RequestBody String text){
        try {
            notesService.changeNote(id,text);
        } catch (InstanceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND NOTE WITH THAT ID");
        }
        throw new ResponseStatusException(HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "notes/{id}")
    public void removeNote(@PathVariable int id){
        try {
            notesService.removeNote(id);
        } catch (InstanceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND NOTE WITH THAT ID");
        }
        throw new ResponseStatusException(HttpStatus.ACCEPTED);
    }
}
