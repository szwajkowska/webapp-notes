package pl.ania.notes.program.api;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ania.notes.program.domain.UserList;
import pl.ania.notes.program.domain.UserModel;

import javax.validation.Valid;

@Controller
@RequestMapping("/signIn")
public class SignInController {

    private UserList userList;
    private UserValidator userValidator;

    public SignInController(UserList userList, UserValidator userValidator) {
        this.userList = userList;
        this.userValidator = userValidator;
    }

    @GetMapping
    String signIn(UserModel userModel) {
        return "signIn";
    }

    @PostMapping
    String createNewUser(@Valid @ModelAttribute UserModel userModel, BindingResult result) {
        userValidator.validate(userModel, result);
        if (result.hasErrors()) {
            return "signIn";
        }
        userList.addUser(userModel.getUsername(), userModel.getPassword());
        return "redirect:/login?signedin";
    }
}
