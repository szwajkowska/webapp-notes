package pl.ania.notes;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    NotesRepository notesRepository;

    @Test
    public void shouldAddNote(){
        //given
        String body = "body";
        NoteRequest noteRequest = new NoteRequest(body);
        //when
        URI uri = testRestTemplate.postForLocation("/notes", noteRequest);
        //then
        Assert.assertEquals(1, notesRepository.getNotesMap().size());
        Assert.assertEquals(body, notesRepository.getNotesMap().get(1L).getBody());

        //when
        Note note = testRestTemplate.getForObject(uri.toString(), Note.class);
        //then
        Assert.assertEquals(body, note.getBody());

        //when
        testRestTemplate.delete(uri.toString());
        //then
        Assert.assertEquals(0, notesRepository.getNotesMap().size());
        //and
        ResponseEntity<Note> responseEntity = testRestTemplate.getForEntity(uri.toString(), Note.class);
        Assert.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

    }
}
