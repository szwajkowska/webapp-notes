package pl.ania.notes;

import org.junit.Assert;
import org.junit.Test;



import static org.junit.Assert.*;

/**
 * Created by lukasz on 2017-01-31.
 */
public class NotesRepositoryTest {

    private NotesRepository notesRepository = new NotesRepository();

    @Test
    public void shouldAddNoteToList() throws Exception {
        //given
        Note note = new Note(null, "body");

        //when
        notesRepository.addNote(note);

        //then
        Assert.assertEquals(1, notesRepository.getNotesMap().size());
    }

    @Test
    public void shouldDeleteNoteFromList() throws Exception {
        //given
        Note note = new Note(1L, "body");
        notesRepository.getNotesMap().put(note.getId(), note);

        //when
        notesRepository.delete(1);

        //then
        Assert.assertFalse(notesRepository.getNotesMap().containsKey(note.getId()));
    }

    @Test
    public void shouldReplaceNote() throws Exception {
        //given
        Note noteOne = new Note(null, "xxx");
        Note noteTwo = new Note(notesRepository.addNote(noteOne), "yyy");

        //when
        notesRepository.replaceNote(noteTwo);

        //then
        Assert.assertEquals(1, notesRepository.getNotesMap().size());
        Assert.assertFalse(notesRepository.getNotesMap().containsValue(noteOne));
        Assert.assertTrue(notesRepository.getNotesMap().containsValue(noteTwo));
        //czemu nie działa?
    }

}