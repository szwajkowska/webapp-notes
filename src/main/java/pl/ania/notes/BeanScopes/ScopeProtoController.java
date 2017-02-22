package pl.ania.notes.BeanScopes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proto")
public class ScopeProtoController {

    private static final Logger logger = LoggerFactory.getLogger(ScopeProtoController.class);


    private final Language customer;
    private final Name customer2;

    public ScopeProtoController(Language customer, Name customer2) {
        this.customer = customer;
        this.customer2 = customer2;


        logger.info("{}", customer);
    }

    @RequestMapping("/nameSingleton")
    public String name() {
        String result = customer2.getFirstName();
        customer2.setFirstName("Adam");
        return "init Data: " + result + "|-----| modified Data: " + customer2.getFirstName();
    }

    @RequestMapping("/namecheckSingleton")
    public String namecheck() {
        return "check Data: " + customer2.getFirstName();
    }



    @RequestMapping("/languagePrototype")
    public String language() {
        String result = customer.getLanguage();
        //customer.setCustomerLanguage("French") //this command creates new instance of Language
        return "init Data: " + result + "|-----| modified Data: " + customer.setLanguage("French");
    }

    @RequestMapping("/languagecheckPrototype")
    public String languagecheck() {
        logger.info("{}", customer);
        return "check Data: " + customer.getLanguage();
    }

//    @RequestMapping("/addressRequest")
//    public String address() {
//        String result = customer.getCustomerAddress();
//        customer.setCustomerAddress("EU");
//        return "init Data: " + result + "|-----| modified Data: " + customer.getCustomerAddress();
//    }
//
//    @RequestMapping("/addresscheckRequest")
//    public String addresscheck() {
//        return "check Data: " + customer.getCustomerAddress();
//    }

//    @RequestMapping("/ageSession")
//    public String age() {
//        String result = customer.getCustomerAge();
//        customer.setCustomerAge("25");
//        return "init Data: " + result + "|-----| modified Data: " + customer.getCustomerAge();
//    }
//
//    @RequestMapping("/agecheckSession")
//    public String agecheck() {
//        return "check Data: " + customer.getCustomerAge();
//    }
}
