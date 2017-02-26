package pl.ania.notes;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope//skąd się wzięłą ta adnotacja?
public class NoteService {

    private UserRepository userRepository;

    public NoteService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long save(NoteRequest noteRequest, String user) {
        long id = userRepository.addNote(new Note(null, noteRequest.getBody()), user);
        return id;
    }

    public void delete(long id, String user) { //czy muszą tu być te metody: delete i replace?
        userRepository.delete(id, user);
    }


    public List<Note> getList(String user) {
        return new ArrayList<>(userRepository.getNotesMap(user).values());
    }

    public Note findNoteById(Long id, String user) {
        Note note = userRepository.getNotesMap(user).get(id);
        return note;
    }

    void update(NoteRequest noteRequest, Long id, String user){
        userRepository.replaceNote(new Note(id, noteRequest.getBody()), user);
    }

}
