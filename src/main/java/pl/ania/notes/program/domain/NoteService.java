package pl.ania.notes.program.domain;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.UUID;

@Service
@SessionScope
public class NoteService {

    private NotesRepository notesRepository;
    private NotesRepositoryMongo notesRepositoryMongo;

    public NoteService(NotesRepository notesRepository, NotesRepositoryMongo notesRepositoryMongo) {
        this.notesRepository = notesRepository;
        this.notesRepositoryMongo = notesRepositoryMongo;
    }

    public String save(NoteRequest noteRequest, String user) {
        String id = notesRepository.addNote(new Note(UUID.randomUUID().toString(), noteRequest.getBody(), user));
        return id;
    }

    public void delete(String id) { //czy muszą tu być te metody: delete i replace?
        notesRepository.delete(id);
    }

    public List<Note> getAll(String user) {
        return notesRepositoryMongo.findByUsername(user);
    }

    public Note findNoteById(String id) {
        Note note = notesRepositoryMongo.findOne(id);
        return note;
    }

    void update(NoteRequest noteRequest, String id, String user){
        notesRepository.replaceNote(new Note(id, noteRequest.getBody(), user));
    }

}
