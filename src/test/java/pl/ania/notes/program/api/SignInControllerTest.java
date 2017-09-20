package pl.ania.notes.program.api;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class SignInControllerTest extends ControllerTest {

    @Test
    public void shouldCreateNewUser() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/signIn")
                        .param("username", "user")
                        .param("password", "xxxx")
                        .param("confPassword", "xxxx"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
        Assertions.assertThat(userRepositoryMongo.count()).isEqualTo(1);
        Assertions.assertThat(userRepositoryMongo.findByUsername("user").getPassword()).isEqualTo("xxxx");
    }
}
