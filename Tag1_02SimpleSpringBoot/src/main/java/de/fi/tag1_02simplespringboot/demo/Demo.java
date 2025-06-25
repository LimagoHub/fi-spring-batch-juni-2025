package de.fi.tag1_02simplespringboot.demo;


import de.fi.tag1_02simplespringboot.translator.Translator;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("singleton")
@Scope("singleton")
//@Lazy
public class Demo {



    private final String message ;
    private final Translator translator;

    //@Autowired
    public Demo( final Translator translator,@Value("${Demo.gruss}") final String message) {
        this.translator = translator;
        this.message = message;
        System.out.println(translator.translate("Ctor demo"));
        System.out.println(translator.translate(message));
    }



    @PostConstruct
    public void doIt() {
        System.out.println(translator.translate(message));
    }

    @PreDestroy
    public void delete() {
        System.out.println("..und tschuess");
    }

    public void foo() {
        System.out.println(translator.translate("Foo"));
    }
}
