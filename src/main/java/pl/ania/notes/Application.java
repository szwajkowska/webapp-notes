package pl.ania.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    SpringResourceTemplateResolver springResourceTemplateResolver() {
        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
        springResourceTemplateResolver.setSuffix(".html");
        springResourceTemplateResolver.setPrefix("classpath:/templates/");
        springResourceTemplateResolver.setTemplateMode("HTML");
        springResourceTemplateResolver.setCacheable(false);
        springResourceTemplateResolver.setCharacterEncoding("UTF-8");
        return springResourceTemplateResolver;
    }

    @Bean
    SpringTemplateEngine springTemplateEngine() {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(springResourceTemplateResolver());
        return springTemplateEngine;
    }

    @Bean
    ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(springTemplateEngine());
        thymeleafViewResolver.setCharacterEncoding(UTF_8.name());
        return thymeleafViewResolver;
    }

}
