package pl.ania.notes;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class NotesRepository {



    private final Map<Long, Note> notesMap = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public Map<Long, Note> getNotesMap() {
        return notesMap;
    }

    public long addNote(Note note) {
        if (note.getId() != null) {
            throw new IllegalArgumentException("notes id must be null");
        }
        long id = counter.incrementAndGet();
        notesMap.put(id, new Note(id, note.getBody()));
        return id;
    }

    public void delete(long id) { //czy nie lepiej zrobic id jako parametr?   zmieniłam na void
        notesMap.remove(id);

    }


    public Note replaceNote(Note note) {
        notesMap.put(note.getId(), note);
        return note;
    }
}
