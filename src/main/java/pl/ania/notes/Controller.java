package pl.ania.notes;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/notesList")
public class Controller {

    private final NoteService noteService;

    public Controller(NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> showList(){
        return noteService.getList();

    }

    @PostMapping
    public ResponseEntity<Void> addNote(@RequestBody NoteRequest noteRequest){
        long id = noteService.changeNoteRequestToNote(noteRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("xxx", "yyy"); //co tu powinnam wpisać? Nie wiem do czego to się odnosi
        httpHeaders.setLocation(URI.create("/notesList/" + id));
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
        //lub: return ResponseEntity.created(URI.create("/notes/" + id)).build();

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Note> showNoteById(@PathVariable Long id){
        Note note = noteService.findNoteById(id);
        if (id == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(note, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteNote(@PathVariable Long id){
        Note note = noteService.findNoteById(id); //czy nie lepiej zrobic tak zeby nie musieć ciągle szukac danej notatki?
        noteService.delete(note);
//        noteService.getList().remove(note); //czy to jest dobrze? BO w takim razie w ogóle nie jest używana metoda delete
        // z NotesRepository.
    }

    @PutMapping(path = "/{id}")
    public void changeNote(@RequestBody NoteRequest noteRequest, @PathVariable Long id){
        Note note = noteService.findNoteById(id);
        noteService.replaceNote(note);

    }
}















