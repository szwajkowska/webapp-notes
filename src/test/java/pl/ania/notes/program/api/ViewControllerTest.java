package pl.ania.notes.program.api;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.ania.notes.program.domain.Note;

import java.security.Principal;

public class ViewControllerTest extends ControllerTest {

    @Test
    public void shouldShowNotes() throws Exception {
        Principal principal = () -> "name";

        mockMvc.perform(
                MockMvcRequestBuilders.get("/main")
                        .principal(principal)
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldAddNote() throws Exception {
        Principal principal = () -> "name";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/main")
                        .principal(principal)
                        .param("body", "body")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertThat(notesRepositoryMongo.count()).isEqualTo(1);
        Assertions.assertThat(notesRepositoryMongo.findByUsername("name").get(0).getBody()).isEqualTo("body");
    }

    @Test
    public void shouldShowNoteById() throws Exception{
        Note note = new Note("1", "body", "name");
        notesRepositoryMongo.save(note);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/main/1")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertThat(notesRepositoryMongo.count()).isEqualTo(1);
    }

    @Test
    public void shouldDeleteNote() throws Exception{
        Note note = new Note("1", "body", "name");
        Note note2 = new Note("2", "body2", "name2");
        notesRepositoryMongo.save(note);
        notesRepositoryMongo.save(note2);
        Principal principal = () -> "name";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/main/1")
                        .principal(principal)
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        Assertions.assertThat(notesRepositoryMongo.count()).isEqualTo(1);
        Assertions.assertThat(notesRepositoryMongo.findOne("2").getBody()).isEqualTo("body2");
        Assertions.assertThat(notesRepositoryMongo.findAll().get(0).getBody()).isEqualTo("body2");
    }
}
