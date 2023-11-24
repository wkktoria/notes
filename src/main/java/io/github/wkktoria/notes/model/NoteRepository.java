package io.github.wkktoria.notes.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface NoteRepository {
    List<Note> findAll();

    Page<Note> findAll(Pageable pageable);

    Optional<Note> findById(Integer id);

    Note save(Note entity);

    boolean existsById(Integer id);

    void deleteById(Integer id);
}
