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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
		Note addedNote = objectMapper.readValue(mockMvc.perform(post("/notes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new Note("test author","test text"))))
				.andReturn()
				.getResponse()
				.getContentAsString()
				,Note.class);
		Note getNote = objectMapper.readValue(mockMvc.perform(get("/notes/"+addedNote.getId()))
				.andReturn()
				.getResponse()
				.getContentAsString()
				,Note.class);
		assertEquals(addedNote,getNote);
	}

	@Test
	void getAllNotes() throws Exception {
		Note addedNote = objectMapper.readValue(mockMvc.perform(post("/notes")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(new Note("Author","testing get all"))))
						.andReturn()
						.getResponse()
						.getContentAsString()
						,Note.class);
		assertTrue(mockMvc.perform(get("/notes"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString()
				.contains("testing get all"));
	}

	@Test
	void changeNote() throws Exception{
		Note addedNote = objectMapper.readValue(mockMvc.perform(post("/notes")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(new Note("author","text before change"))))
						.andReturn()
						.getResponse()
						.getContentAsString()
						,Note.class);
				mockMvc.perform(put("/notes/"+addedNote.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content("text after change"))
						.andExpect(status().isOk());
		assertTrue(mockMvc.perform(get("/notes/"+addedNote.getId()))
						.andReturn()
						.getResponse()
						.getContentAsString()
						.contains("text after change"));
	}

	@Test
	void deleteNote() throws Exception{
		Note addedNote = objectMapper.readValue(mockMvc.perform(post("/notes")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(new Note("author","text before change"))))
						.andReturn()
						.getResponse()
						.getContentAsString()
						,Note.class);
		mockMvc.perform(delete("/notes/"+addedNote.getId()))
				.andExpect(status().isOk());
		mockMvc.perform(get("/notes/"+addedNote.getId()))
				.andExpect(status().is(404));
	}

	@Test
	 void deleteAllNotes() throws Exception{
		mockMvc.perform(delete("/notes/deleteAll"))
				.andExpect(status().isOk());
		assertEquals("[]",	mockMvc.perform(get("/notes"))
									.andReturn()
									.getResponse()
									.getContentAsString());
	}
}
