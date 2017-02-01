package pl.ania.notes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/notesList")
public class Controller {

    private final NoteService noteService;

    public Controller(NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> showList(){
        return noteService.getList();

    }
}
