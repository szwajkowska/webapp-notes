package pl.ania.notes.program.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NotesRepository {

    @Autowired
    private NotesRepositoryMongo notesRepositoryMongo;

    public NotesRepository(NotesRepositoryMongo notesRepositoryMongo) {
        this.notesRepositoryMongo = notesRepositoryMongo;
    }

    public String addNote(Note note) {
       return notesRepositoryMongo.save(note).getId();
    }

    public void delete(String id) {
        notesRepositoryMongo.delete(id);
    }

    public Note replaceNote(Note note) {
        return notesRepositoryMongo.save(note);

    }
}
