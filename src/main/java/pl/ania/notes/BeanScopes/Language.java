package pl.ania.notes.BeanScopes;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class Language {

    private String language = "English";

    public Language() {
        System.out.println("Create new Language: " + this.language);
    }

    public String getLanguage() {
        return language;
    }

    public String setLanguage(String language) {
        this.language = language;
        return this.language;
    }
}
