package io.github.wkktoria.notes.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlNoteRepository extends NoteRepository, JpaRepository<Note, Integer> {
}
