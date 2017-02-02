package pl.ania.notes;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class NoteService {

    private NotesRepository notesRepository;

    public NoteService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    //zrobiłam zmianę (zwraca id).Dobrze?
    public long changeNoteRequestToNote(NoteRequest noteRequest){
        long id = notesRepository.addNote(new Note(null, noteRequest.getBody()));
        return id;
    }

    public void delete(Note note){ //czy muszą tu być te metody: delete i replace?
        notesRepository.delete(note);
    }

    public void replaceNote(Note note){
        notesRepository.replaceNote(note);
    }

    public List<Note> getList(){
        return new ArrayList<>(notesRepository.getNotesMap().values());
    }

    //dodałam metodę
    public Note findNoteById(Long id) {
        Note note = notesRepository.getNotesMap().get(id);
        return note;
    }
}
