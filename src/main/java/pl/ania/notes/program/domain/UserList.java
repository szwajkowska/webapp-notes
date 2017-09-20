package pl.ania.notes.program.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserList {

    @Autowired
    private UserRepositoryMongo userRepositoryMongo;

    public void addUser(String username, String password) {
        userRepositoryMongo.save(new User(username, password));
    }

    public Optional<User> getUser(String username) {
        return Optional.ofNullable(userRepositoryMongo.findByUsername(username));
    }
}


