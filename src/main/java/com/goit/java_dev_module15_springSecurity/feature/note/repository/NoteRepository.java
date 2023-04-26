package com.goit.java_dev_module15_springSecurity.feature.note.repository;

import com.goit.java_dev_module15_springSecurity.feature.note.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
