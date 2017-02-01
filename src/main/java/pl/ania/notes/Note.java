package pl.ania.notes;

public class Note {

    private String body;
    private final Long id;

    public Note(Long id, String body) {  //czy notatka powinna mieć id skoro id w mapie jest inne?
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
