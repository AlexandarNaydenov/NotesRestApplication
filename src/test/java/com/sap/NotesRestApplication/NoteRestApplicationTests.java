package com.sap.NotesRestApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.NotesRestApplication.Notes.Note;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class NoteRestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Before
	@Test
	public void addNote() throws Exception {
		mockMvc.perform(post("/notes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new Note("test author","test text"))))
				.andExpect(status().is(202));
	}

	@Test
	void getAllNotes() throws Exception {
		assertTrue(mockMvc.perform(get("/notes"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString()
				.contains("test author"));
	}

	@Test
	void changeNote() throws Exception{
			mockMvc.perform(put("/notes/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("changed text"))
				.andExpect(status().is(202));
	}

	
}
