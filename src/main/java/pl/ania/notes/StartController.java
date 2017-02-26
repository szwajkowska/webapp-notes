package pl.ania.notes;


import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class StartController {

    @GetMapping
    String showLoginPage(){
        return "goToLoginPage";
    }


}
