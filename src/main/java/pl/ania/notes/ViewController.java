package pl.ania.notes;

import org.springframework.http.HttpHeaders;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.security.Principal;

@org.springframework.stereotype.Controller
@RequestMapping(path = "/test")
public class ViewController {
    private final NoteService noteService;

    public ViewController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    String test(ModelMap model, Principal principal) {
        String name = principal.getName();
        model.put("notes", noteService.getList(name));
        return "test";  //jak zrobić żeby wyświetlał się plik list.jsp?
    }

    @GetMapping("/login")
    String log(){
        return "login";
    }

    @PostMapping
//    String addNote(@RequestBody NoteRequest noteRequest, ModelMap model){
    String addNote(@RequestParam(value = "body") String body, ModelMap model, HttpServletResponse response,
                   Principal principal) {
        String name = principal.getName();
        NoteRequest noteRequest = new NoteRequest(body);
        long id = noteService.save(noteRequest, name);
        response.addHeader("Location", "/test/" + id );
        model.put("notes", noteService.getList(name));
        return "test";

    }

//    @GetMapping("/a")
//    String showPage(){
//        return "login";
//    }

    @GetMapping(path = "/{id}")
    String showNoteById(@PathVariable long id, ModelMap model, Principal principal) {
        String name = principal.getName();
        Note note = noteService.findNoteById(id, name);

        if (note == null) {
            return "wrong_id";
        }
        String body = note.getBody();
        model.put("body", body);
        model.put("id", id);
        return "note";
    }

    @PostMapping(path = "/{id}")
    String deleteNote(@PathVariable long id, ModelMap model, Principal principal) {
        String name = principal.getName();
        noteService.delete(id, name);
        model.put("notes", noteService.getList(name));
        return "redirect:/test";
    }


}
