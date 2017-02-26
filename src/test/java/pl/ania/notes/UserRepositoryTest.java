package pl.ania.notes;

import org.junit.Assert;
import org.junit.Test;

public class UserRepositoryTest {

    private UserRepository userRepository = new UserRepository();

    @Test
    public void shouldAddUserToMap(){
        //given
        String user1 = "usesr1";
        String user2 = "user2";
        Note note1 = new Note(null, user1);
        Note note11 = new Note(null, user1);
        Note note2 = new Note(null, user2);
        Note note22 = new Note(null, user2);
        Note note222 = new Note(null, user2);
        //when
        long L1 = userRepository.addNote(note1, user1);
        long L11 = userRepository.addNote(note11, user1);
        long L2 = userRepository.addNote(note2, user2);
        long L22 = userRepository.addNote(note22, user2);
        long L222 = userRepository.addNote(note222, user2);

        //then
        Assert.assertEquals(2, userRepository.getNotesMap(user1).size());
        Assert.assertEquals(3, userRepository.getNotesMap(user2).size());
        Assert.assertTrue(userRepository.getNotesMap(user1).containsKey(L1));
        Assert.assertTrue(userRepository.getNotesMap(user1).containsKey(L11));
        Assert.assertTrue(userRepository.getNotesMap(user2).containsKey(L2));
        Assert.assertTrue(userRepository.getNotesMap(user2).containsKey(L22));


    }
}
