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
    private TestRestTemplate testRestTemplate;

    @Autowired//czemu jest autowired? Bo nie ma konstruktora?
    private NotesRepository notesRepository;

    @Test
    public void shouldAddNote(){
        //given
        String body = "body";
        NoteRequest noteRequest = new NoteRequest(body);
        //whent
        URI uri = testRestTemplate.postForLocation("/notes", noteRequest);//my (test) wysylamy posta do kontrolera
//        ResponseEntity response = testRestTemplate.postForEntity("/test", noteRequest, Void.class);//my (test) wysylamy posta do kontrolera
//        System.out.println(response);
//        URI uri = response.getHeaders().getLocation();
        System.out.println(uri);
        //then
        Assert.assertEquals(1, notesRepository.getNotesMap().size());
        Assert.assertEquals(body, notesRepository.getNotesMap().get(1L).getBody());

        //when
        Note note = testRestTemplate.getForObject(uri.toString(), Note.class);
        //then
        Assert.assertEquals(body, note.getBody());

        //when
        testRestTemplate.delete(uri.toString());//skad sie bierze w ścieżce id?
        //then
        Assert.assertEquals(0, notesRepository.getNotesMap().size());
        //and
        ResponseEntity<Note> responseEntity = testRestTemplate.getForEntity(uri.toString(), Note.class);
        Assert.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

    }
}
