package pl.ania.notes.program.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserModel {

    @NotEmpty(message = "field can't be empty")
    @Length(min = 3, message = "length must have 3 or more chars")
    private String username;
    @Length(min = 3, message = "length must have 3 or more chars")
    private String password;

    @Length(min = 3, message = "length must have 3 or more chars")
    private String confPassword;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }
}
