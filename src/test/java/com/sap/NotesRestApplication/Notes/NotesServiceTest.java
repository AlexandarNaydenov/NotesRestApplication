package com.sap.NotesRestApplication.Notes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
class NotesServiceTest {

    @Autowired
    private NotesService notesService;
    static private ArrayList<Note> list;

    @MockBean
    private NotesRepository notesRepository;

    @BeforeAll
    static void addSomeInitNotes(){
        list = new ArrayList<>();
        list.add(new Note("Alex","First Note"));
        list.add(new Note("Ivan","Second Note"));
        list.add(new Note("Maria","Third Note"));
    }

    @Test
    void getAllNotes() {
        Mockito.when(notesRepository.findAll()).thenReturn(list);
        ArrayList<Note> noteList = new ArrayList<>(notesService.getAllNotes());
        assertEquals(noteList.get(1).getAuthor(),"Ivan");
    }

    @Test
    void getNote() throws InstanceNotFoundException {
        Mockito.when(notesRepository.findById(3)).thenReturn(java.util.Optional.ofNullable(list.get(2)));
        assertEquals(notesService.getNote(3).getAuthor(),"Maria");
    }

    @Test
    void changeNote() throws InstanceNotFoundException {
    }

    @Test
    void removeNote() {
    }
}