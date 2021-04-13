package com.sap.NotesRestApplication.Notes;

import java.time.LocalDateTime;
import java.util.Objects;

public class Note {
    private String author;
    private String text;
    private final LocalDateTime creationTime;
    private LocalDateTime modificationTime;

    public Note(String author) {
        this.author = author;
        this.creationTime = LocalDateTime.now();
        this.modificationTime = LocalDateTime.now();
    }

    public Note(String author, String text) {
        this.author = author;
        this.text = text;
        this.creationTime = LocalDateTime.now();
        this.modificationTime = LocalDateTime.now();
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public LocalDateTime getModificationTime() {
        return modificationTime;
    }

    public void setAuthor(String author) {
        this.author = author;
        this.modificationTime = LocalDateTime.now();
    }

    public void setText(String text) {
        this.text = text;
        this.modificationTime = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return author.equals(note.author) && Objects.equals(text, note.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, text);
    }
}
