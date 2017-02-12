package pl.ania.notes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Note {

    private String body;
    private final Long id;

    @JsonCreator
    public Note(@JsonProperty("id") Long id, @JsonProperty("body") String body) {  //czy notatka powinna mieć id skoro id w mapie jest inne?
        this.body = body;
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public Long getId() {
        return id;
    }
}
