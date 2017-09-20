package pl.ania.notes.program.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class NoteRequest {

    private final String body;

    @JsonCreator
    public NoteRequest(@JsonProperty("body") String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
