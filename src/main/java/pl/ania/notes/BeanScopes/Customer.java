package pl.ania.notes.BeanScopes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Customer {

    private static final Logger logger = LoggerFactory.getLogger(Customer.class);

    private final Name name;

    @Autowired
    private Age age;

    @Autowired //czy tutaj adnotacja jest potrzebna?
    private Address address;


    private final Language language;

    public Customer(Name name, Language language) {
        this.name = name;
        this.language = language;
        logger.info("{}",language);
    }

    public String getCustomerName() {
        return name.toString();
    }

    public void setCustomerName(String firstName, String lastName) {
        this.name.setFirstName(firstName);
        this.name.setLastName(lastName);
    }

    public String getCustomerAge(){
        return age.getAge();
    }

    public void setCustomerAge(String age){
        this.age.setAge(age);
    }

    public String getCustomerAddress(){
        return address.getAddress();
    }

    public void setCustomerAddress(String address){
        this.address.setAddress(address);
    }

    public String getCustomerLanguage(){
        return language.getLanguage();
    }

    public String setCustomerLanguage(String language){
        return this.language.setLanguage(language);
    }
}
