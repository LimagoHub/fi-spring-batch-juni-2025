package de.fi.tag1_02simplespringboot.translator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Primary
//@Qualifier("upper")
@Profile("test")
public class ToUpperTranslator implements Translator {
    @Override
    public String translate(final String message) {
        return message.toUpperCase();
    }
}
