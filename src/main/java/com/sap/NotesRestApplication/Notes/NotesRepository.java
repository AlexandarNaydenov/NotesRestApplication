package com.sap.NotesRestApplication.Notes;


import org.springframework.data.repository.CrudRepository;

import javax.validation.Valid;
import java.util.List;

public interface NotesRepository extends CrudRepository<Note,Integer> {

    public List<Note> findAllByAuthor(@Valid String author);
}
