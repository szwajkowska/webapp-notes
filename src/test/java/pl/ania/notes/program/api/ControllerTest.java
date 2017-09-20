package pl.ania.notes.program.api;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.ania.notes.program.Application;
import pl.ania.notes.program.domain.NotesRepositoryMongo;
import pl.ania.notes.program.domain.UserRepositoryMongo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class, FongoConfiguration.class})
@SpringBootTest
@ActiveProfiles("test")
public abstract class ControllerTest {

    @Autowired
    WebApplicationContext ctx;

    MockMvc mockMvc;

    @Autowired
    UserRepositoryMongo userRepositoryMongo;

    @Autowired
    NotesRepositoryMongo notesRepositoryMongo;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(ctx)
                .build();
    }

    @After
    public void clear() {
        userRepositoryMongo.deleteAll();
        notesRepositoryMongo.deleteAll();
    }
}
