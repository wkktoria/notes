package io.github.wkktoria.notes.controller;

import io.github.wkktoria.notes.model.Note;
import io.github.wkktoria.notes.model.NoteRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
class NoteController {
    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);
    private final NoteRepository repository;

    NoteController(final NoteRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/notes")
    ResponseEntity<Note> createNote(@Valid @RequestBody Note toCreate) {
        logger.info("Creating note");

        var result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @GetMapping(value = "/notes", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Note>> readAllNotes() {
        logger.info("Reading all notes");

        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/notes")
    ResponseEntity<List<Note>> readAllNotes(Pageable pageable) {
        logger.info("Reading all notes (custom pageable)");

        return ResponseEntity.ok(repository.findAll(pageable).getContent());
    }

    @GetMapping("/notes/{id}")
    ResponseEntity<Note> readNoteById(@PathVariable int id) {
        logger.info("Reading note by id");

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/notes/{id}")
    ResponseEntity<Note> updateNote(@PathVariable int id, @Valid @RequestBody Note toUpdate) {
        logger.info("Updating note");

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        toUpdate.setId(id);
        repository.save(toUpdate);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/notes/{id}")
    ResponseEntity<Note> deleteNote(@PathVariable int id) {
        logger.info("Deleting note");

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
