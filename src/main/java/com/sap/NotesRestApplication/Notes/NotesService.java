package com.sap.NotesRestApplication.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.*;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    public NotesService(){
    }

    public List<Note> getAllNotes(){
        List<Note> list = new ArrayList<>();
        notesRepository.findAll().forEach(list::add);
        return list;
    }

    public Note getNote(int id) throws InstanceNotFoundException {
        if(notesRepository.findById(id).isPresent()){
            return notesRepository.findById(id).get();
        }else {
            throw new InstanceNotFoundException();
        }
    }

    public void addNote(String author, String text) {
       notesRepository.save(new Note(author,text));
    }

    public void changeNote(int id, String newText) throws InstanceNotFoundException {
        if(notesRepository.findById(id).isPresent()){
           Note temp = notesRepository.findById(id).get();
           temp.setText(newText);
           notesRepository.save(temp);
        }else{
            throw new InstanceNotFoundException();
        }
    }

    public void removeNote(int id) throws InstanceNotFoundException {
        if(notesRepository.findById(id).isPresent()){
            notesRepository.deleteById(id);
        }else{
            throw new InstanceNotFoundException();
        }
    }
}
