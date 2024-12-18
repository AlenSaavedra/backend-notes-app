package com.patrickmolina.notes.notes.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.patrickmolina.notes.notes.Model.Note;

import java.time.LocalDate;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByArchivedFalse();  // Notas activas
    List<Note> findByArchivedTrue();   // Notas archivadas
    
    // Método para obtener notas filtradas por estado de archivo (archivadas o activas)
    List<Note> findByArchived(boolean archived);

    // Método para obtener notas filtradas por la fecha de creación exacta
    List<Note> findByCreatedAt(LocalDate createdAt);

    // Método para obtener notas filtradas por fecha posterior a la fecha proporcionada
    List<Note> findByCreatedAtAfter(LocalDate date);

    // Método para obtener notas filtradas por estado y fecha
    List<Note> findByArchivedAndCreatedAtAfter(boolean archived, LocalDate date);
}
