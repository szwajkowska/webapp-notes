package pl.ania.notes.program.api;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import pl.ania.notes.program.domain.UserModel;

@Component
public class UserValidator implements org.springframework.validation.Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserModel userModel = (UserModel) target;
        String password = userModel.getPassword();
        String confPassword = userModel.getConfPassword();
        if (!password.equals(confPassword)) {
            errors.rejectValue("password", "userModel.password.missMatch");
        }
    }
}
