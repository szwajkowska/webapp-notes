package pl.ania.notes;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope//skąd się wzięłą ta adnotacja?
public class NoteService {

    private NotesRepository notesRepository;

    public NoteService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public long save(NoteRequest noteRequest) {
        long id = notesRepository.addNote(new Note(null, noteRequest.getBody()));
        return id;
    }

    public void delete(long id) { //czy muszą tu być te metody: delete i replace?
        notesRepository.delete(id);
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
