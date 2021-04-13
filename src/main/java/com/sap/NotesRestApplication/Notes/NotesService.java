package com.sap.NotesRestApplication.Notes;

import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.*;

@Service
public class NotesService {

    private Map<Integer,Note> notes;
    private int id;

    public NotesService(){
        this.notes = new HashMap<>();
        this.id = 1;
        this.addNote("Alex","FirstNode");
    }

    public Map<Integer,Note> getAllNotes(){
        return notes;
    }

    public Note getNote(int id){
        return notes.get(id);
    }

    public void addNote(String author, String text) {
         this.notes.put(id,new Note(author, text));
         this.id++;
    }

    public void changeNote(int id, String newText) throws InstanceNotFoundException {
        if(notes.containsKey(id)) {
            notes.get(id).setText(newText);
        }else{
            throw new InstanceNotFoundException("There is no Note with that id");
        }
    }

    public void removeNote(int id) throws InstanceNotFoundException {
        if(notes.containsKey(id)){
            notes.remove(id);
        }else{
            throw new InstanceNotFoundException("There is no Note with that id");
        }
    }
}
