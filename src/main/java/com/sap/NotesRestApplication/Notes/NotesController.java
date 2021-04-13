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

    @RequestMapping(method = RequestMethod.PUT, value = "/notes")
    public String changeNote(@RequestBody Note note){
        try {
            notesService.changeNote(Integer.parseInt(note.getAuthor()),note.getText());
        } catch (InstanceNotFoundException e) {
            return "Not found note with that ID";
        }
        return "Changed";
    }

    
}
