package com.sap.NotesRestApplication.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
public class NotesController {

    @Autowired
    private NotesService notesService;

    @RequestMapping("/notes")
    public Map<Integer,Note> getAllNotes(){
        return notesService.getAllNotes();
    }

    @RequestMapping("notes/{id}")
    public Note getNote(@PathVariable int id){
        return notesService.getNote(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/notes")
    public void addNote(@RequestBody Note note){
        notesService.addNote(note.getAuthor(),note.getText());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/notes/{id}")
    public String changeNote(@PathVariable int id, @RequestBody String text){
        try {
            notesService.changeNote(id,text);
        } catch (InstanceNotFoundException e) {
            return "Not found note with that ID";
        }
        return "Changed";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "notes/{id}")
    public String removeNote(@PathVariable int id){
        try {
            notesService.removeNote(id);
        } catch (InstanceNotFoundException e) {
            return "Not found note with that ID";
        }
        return "Removed";
    }
}
