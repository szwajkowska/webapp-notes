package pl.ania.notes.program.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotesRepositoryMongo extends MongoRepository<Note, String> {

    List<Note> findByUsername(String user);
}
