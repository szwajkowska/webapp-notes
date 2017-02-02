package pl.ania.notes;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    private NotesRepository notesRepository;

    public NoteService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public long save(NoteRequest noteRequest) {
        long id = notesRepository.addNote(new Note(null, noteRequest.getBody()));
        return id;
    }

    public void delete(Note note) { //czy muszą tu być te metody: delete i replace?
        notesRepository.delete(note);
    }


    public List<Note> getList() {
        return new ArrayList<>(notesRepository.getNotesMap().values());
    }

    public Note findNoteById(Long id) {
        Note note = notesRepository.getNotesMap().get(id);
        return note;
    }

    void update(NoteRequest noteRequest, Long id){
        notesRepository.replaceNote(new Note(id, noteRequest.getBody()));
    }

}
