package pl.ania.notes;


import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
@RequestMapping(path = "/mail")
public class MailController {

    private final SendEmail sendEmail;

    public MailController(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }


    @GetMapping
    String showForm(){
        return "mail";
    }

    @PostMapping
    void sendMail(@RequestParam(value = "body") String body){
        sendEmail.send(body);
    }
}
