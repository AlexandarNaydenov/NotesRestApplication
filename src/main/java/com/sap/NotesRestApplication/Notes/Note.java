package com.sap.NotesRestApplication.Notes;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @Size(min = 1, max = 100)
    private String author;
    private String text;
    private final LocalDateTime creationTime;
    private LocalDateTime modificationTime;

    public Note() {
        this.author="New Author";
        this.creationTime = LocalDateTime.now();
        this.modificationTime = LocalDateTime.now();
    }

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

    public int getId() {return id;};

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
        return id == note.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", creationTime=" + creationTime +
                ", modificationTime=" + modificationTime +
                '}';
    }
}
