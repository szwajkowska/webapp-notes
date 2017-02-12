package pl.ania.notes;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
@RequestMapping(path = "/test")
public class ViewController {
    private final NoteService noteService;

    public ViewController(NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping
    String test(ModelMap model){
        model.put("notes", noteService.getList());
        return "test";  //jak zrobić żeby wyświetlał się plik list.jsp?
    }

    @PostMapping
//    String addNote(@RequestBody NoteRequest noteRequest, ModelMap model){
    String addNote(@RequestParam(value = "body")String body, ModelMap model){
        NoteRequest noteRequest = new NoteRequest(body);
        noteService.save(noteRequest);

        model.put("notes", noteService.getList());
        return "test";

    }

    @GetMapping(path = "/{id}")
    String showNoteById(@PathVariable long id, ModelMap model){
        Note note = noteService.findNoteById(id);

        if (note == null){
            return "wrong_id";
        }
        String body = note.getBody();
        model.put("body", body);
        model.put("id", id);
        return "note";
    }

    @PostMapping(path = "/{id}")
    String deleteNote(@PathVariable long id, ModelMap model){
        noteService.delete(id);
        model.put("notes", noteService.getList());
        return "redirect:/test";
    }










}
