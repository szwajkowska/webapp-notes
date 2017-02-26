package pl.ania.notes;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository {

    public Map<String, NotesRepository> mapsOfUsers = new HashMap<>();

    private Map<String, NotesRepository> addUserToMap(String user){
        if (!mapsOfUsers.containsKey(user)){
            mapsOfUsers.put(user, new NotesRepository());
        }
        return mapsOfUsers;

    }

    private NotesRepository chooseRepository(String user){
        return addUserToMap(user).get(user);
    }

    public Map<Long, Note> getNotesMap(String user) {
        return chooseRepository(user).getNotesMap();
    }

    public long addNote(Note note, String user) {
        return chooseRepository(user).addNote(note);
    }

    public void delete(long id, String user) {
        chooseRepository(user).delete(id);

    }

    public Note replaceNote(Note note, String user) {
        return chooseRepository(user).replaceNote(note);
    }


}
