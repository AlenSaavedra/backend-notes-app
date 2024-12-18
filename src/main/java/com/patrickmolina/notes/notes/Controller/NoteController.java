package com.patrickmolina.notes.notes.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.patrickmolina.notes.notes.Model.Note;
import com.patrickmolina.notes.notes.Service.NoteService;
import java.util.List;


@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "https://fronend-challenge-ensolvers.onrender.com")
public class NoteController {

    @Autowired
    private NoteService noteService;
    
    @CrossOrigin("https://fronend-challenge-ensolvers.onrender.com")
    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteService.createNote(note);
    }
    @CrossOrigin("https://fronend-challenge-ensolvers.onrender.com")
    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody Note noteDetails) {
        return noteService.updateNote(id, noteDetails);
    }
    @CrossOrigin("https://fronend-challenge-ensolvers.onrender.com")
    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
    }

    // Método para archivar una nota
    @CrossOrigin("https://fronend-challenge-ensolvers.onrender.com")
    @PutMapping("/{id}/archive")
    public ResponseEntity<Void> archiveNote(@PathVariable Long id) {
        Note note = noteService.getNoteById(id);
        if (note == null) {
            return ResponseEntity.notFound().build();
        }

        note.setArchived(true);  // Cambia el estado a 'archived'
        noteService.save(note);  // Guarda los cambios
        return ResponseEntity.ok().build();
    }
    @CrossOrigin("https://fronend-challenge-ensolvers.onrender.com")
    @GetMapping("/active")
    public List<Note> getActiveNotes() {
        return noteService.getActiveNotes();
    }
    @CrossOrigin("https://fronend-challenge-ensolvers.onrender.com")
    @GetMapping("/archived")
    public List<Note> getArchivedNotes() {
        return noteService.getArchivedNotes();
    }

    // Endpoint para obtener todas las notas (activas y archivadas)
    @CrossOrigin("https://fronend-challenge-ensolvers.onrender.com")
    @GetMapping
    public List<Note> getAllNotes(@RequestParam(required = false) String status,
                                   @RequestParam(required = false) String date) {
        // Filtrar por estado (activo, archivado o todos) y por fecha
        return noteService.getAllNotes(status, date);
    }
    @CrossOrigin("https://fronend-challenge-ensolvers.onrender.com")
    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }
    
}
