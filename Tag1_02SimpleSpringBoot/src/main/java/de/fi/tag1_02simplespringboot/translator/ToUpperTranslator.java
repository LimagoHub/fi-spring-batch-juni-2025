package de.fi.tag1_02simplespringboot.translator;

import org.springframework.stereotype.Component;

@Component
public class ToUpperTranslator implements Translator {
    @Override
    public String translate(final String message) {
        return message.toUpperCase();
    }
}
