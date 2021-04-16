package com.sap.NotesRestApplication;

import com.sap.NotesRestApplication.Notes.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class NotesRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesRestApplication.class, args);
	}

}
