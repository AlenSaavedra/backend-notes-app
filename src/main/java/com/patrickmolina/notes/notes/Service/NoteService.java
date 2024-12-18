package com.patrickmolina.notes.notes.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrickmolina.notes.notes.Model.Note;
import com.patrickmolina.notes.notes.Repository.NoteRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    // Método para crear una nota
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    // Método para actualizar una nota
    public Note updateNote(Long id, Note noteDetails) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (!optionalNote.isPresent()) {
            throw new RuntimeException("Note not found with id: " + id); // Mejor manejo de excepciones
        }
        Note note = optionalNote.get();
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        note.setArchived(noteDetails.getArchived()); // No olvides actualizar el estado de la nota
        
        return noteRepository.save(note);
    }

    // Método para eliminar una nota
    public void deleteNote(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new RuntimeException("Note not found with id: " + id); // Mejor manejo de excepciones
        }
        noteRepository.deleteById(id);
    }

    // Método para obtener las notas activas
    public List<Note> getActiveNotes() {
        return noteRepository.findByArchivedFalse(); // Esto depende de tu entidad Note
    }

    // Método para obtener las notas archivadas
    public List<Note> getArchivedNotes() {
        return noteRepository.findByArchivedTrue(); // Esto depende de tu entidad Note
    }

    // Método para archivar una nota
    public Note archiveNote(Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (!optionalNote.isPresent()) {
            throw new RuntimeException("Note not found with id: " + id); // Mejor manejo de excepciones
        }
        Note note = optionalNote.get();
        note.setArchived(true); // Archivar la nota
        return noteRepository.save(note);
    }

    // Método para obtener todas las notas con filtros
    public List<Note> getAllNotes(String status, String date) {
        // Filtrado por estado y fecha
        if ("active".equals(status)) {
            return noteRepository.findByArchivedFalse();  // Filtro por notas activas
        } else if ("archived".equals(status)) {
            return noteRepository.findByArchivedTrue();  // Filtro por notas archivadas
        } else if (date != null && !date.isEmpty()) {
            // Filtrado por fecha (suponiendo que 'date' es una cadena que contiene una fecha en formato 'yyyy-MM-dd')
            LocalDate filterDate = LocalDate.parse(date); // Convierte la cadena a LocalDate
            return noteRepository.findByCreatedAtAfter(filterDate); // Filtro por notas creadas después de la fecha proporcionada
        } else {
            return noteRepository.findAll();  // Si no hay filtros, devuelve todas las notas
        }
    }

    // Método para obtener una nota por ID
    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found with id: " + id)); // Mejor manejo de excepciones
    }

    // Método para guardar o actualizar una nota
    public void save(Note note) {
        noteRepository.save(note); // Guardar o actualizar la nota
    }
}
