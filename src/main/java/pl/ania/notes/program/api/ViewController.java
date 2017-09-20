package pl.ania.notes.program.api;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.ania.notes.program.domain.Note;
import pl.ania.notes.program.domain.NoteRequest;
import pl.ania.notes.program.domain.NoteService;
import pl.ania.notes.program.domain.UserList;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@org.springframework.stereotype.Controller
@RequestMapping("/main")
public class ViewController {

    private final NoteService noteService;
    private final UserList userList;

    public ViewController(NoteService noteService, UserList userList) {
        this.noteService = noteService;
        this.userList = userList;
    }

    @GetMapping
    String showNotes(ModelMap model, Principal principal) {
        String name = principal.getName();
        model.put("notes", noteService.getAll(name));
        return "notes";
    }

    @PostMapping
    String addNote(@RequestParam(value = "body") String body, ModelMap model, HttpServletResponse response,
                   Principal principal) {
        String name = principal.getName();
        NoteRequest noteRequest = new NoteRequest(body);
        String id = noteService.save(noteRequest, name);
        response.addHeader("Location", "/main/" + id);
        model.put("notes", noteService.getAll(name));
        return "notes";
    }

    @GetMapping(path = "/{id}")
    String showNoteById(@PathVariable String id, ModelMap model) {
        Note note = noteService.findNoteById(id);

        if (note == null) {
            return "wrong_id";
        }
        String body = note.getBody();
        model.put("body", body);
        model.put("id", id);
        return "note";
    }

    @PostMapping(path = "/{id}")
    String deleteNote(@PathVariable String id, ModelMap model, Principal principal) {
        String name = principal.getName();
        noteService.delete(id);
        model.put("notes", noteService.getAll(name));
        return "redirect:/main";
    }
}