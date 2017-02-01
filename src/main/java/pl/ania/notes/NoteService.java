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

    private void changeNoteRequestToNote(NoteRequest noteRequest){
        notesRepository.addNote(new Note(null, noteRequest.getBody()));
    }

    public List<Note> getList(){
        return new ArrayList<>(notesRepository.getNotesMap().values());
    }


}
