package io.github.wkktoria.notes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "notes")
class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @NotBlank(message = "Note's title cannot be empty")
    private String title;
    private String content;

    Note() {
    }

    public int getId() {
        return Id;
    }

    void setId(final int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    void setTitle(final String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    void setContent(final String content) {
        this.content = content;
    }
}
