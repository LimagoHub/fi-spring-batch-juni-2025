package de.fi.tag1_02simplespringboot.demo;


import de.fi.tag1_02simplespringboot.translator.Translator;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
//@Scope("prototype")
//@Lazy
public class Demo {


    private final Translator translator;

    //@Autowired
    public Demo(final Translator translator) {
        this.translator = translator;
        System.out.println(translator.translate("Ctor demo"));
    }



    @PostConstruct
    public void doIt() {
        System.out.println(translator.translate("doIt"));
    }
}
