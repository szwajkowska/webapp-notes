package pl.ania.notes.program.api;

import com.github.fakemongo.Fongo;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

public class FongoConfiguration {

    @Bean
    MongoDbFactory mongoDbFactory(){
        return new SimpleMongoDbFactory(new Fongo("inMemoryDb").getMongo(), "test");
    }
}
