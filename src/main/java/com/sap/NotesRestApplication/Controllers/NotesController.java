package com.sap.NotesRestApplication.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotesController {

    @RequestMapping("/notes")
    public String getAllNotes(){
        return "Here are my notes";
    }
}
