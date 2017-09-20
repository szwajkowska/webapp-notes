package pl.ania.notes.program.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositoryMongo extends MongoRepository<User, String> {

 User findByUsername(String username);

}