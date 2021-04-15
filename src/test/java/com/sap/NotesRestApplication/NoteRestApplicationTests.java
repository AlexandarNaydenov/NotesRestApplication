package com.sap.NotesRestApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.NotesRestApplication.Notes.Note;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class NoteRestApplicationTests {

	@Autowired
	private  MockMvc mockMvc;

	@Autowired
	private  ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	 void addNote() throws Exception {
		mockMvc.perform(delete("/notes/deleteAll"))
				.andExpect(status().isOk());
		mockMvc.perform(post("/notes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new Note("test author","test text"))))
				.andExpect(status().isOk());
		assertTrue(mockMvc.perform(get("/notes/1"))
				.andReturn()
				.getResponse()
				.getContentAsString()
				.contains("test author"));
	}

	@Test
	void getAllNotes() throws Exception {
		mockMvc.perform(delete("/notes/deleteAll"))
				.andExpect(status().isOk());
		mockMvc.perform(post("/notes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new Note("test author","test text"))))
				.andExpect(status().isOk());
		assertTrue(mockMvc.perform(get("/notes"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString()
				.contains("test author"));
	}

	@Test
	void changeNote() throws Exception{
			mockMvc.perform(delete("/notes/deleteAll"))
				.andExpect(status().isOk());
			mockMvc.perform(post("/notes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new Note("test author","test text"))))
				.andExpect(status().isOk());
			mockMvc.perform(put("/notes/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("changed text"))
				.andExpect(status().isOk());
		assertTrue(mockMvc.perform(get("/notes/1"))
				.andReturn()
				.getResponse()
				.getContentAsString()
				.contains("changed text"));
	}

	@Test
	void deleteNote() throws Exception{
		mockMvc.perform(post("/notes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new Note("test author","test text"))))
				.andExpect(status().isOk());
		mockMvc.perform(delete("/notes/1"))
				.andExpect(status().isOk());
		mockMvc.perform(get("/notes/1"))
				.andExpect(status().is(404));
	}

	@Test
	 void deleteAllNotes() throws Exception{
		mockMvc.perform(delete("/notes/deleteAll"))
				.andExpect(status().isOk());
	}
}
