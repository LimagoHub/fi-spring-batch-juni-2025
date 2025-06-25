package de.fi.tag1_02simplespringboot.service;

import de.fi.tag1_02simplespringboot.persistence.PersonRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Configuration
public class PersonConfig {

    @Bean
    @Lazy
    @Scope("singleton")
    @Qualifier("antipathen")
    public List<String> createAntipathen() {
        return List.of("Attila", "Peter","Paul","Mary");
    }
    @Bean
    @Qualifier("fruits")
    public List<String> getFruits() {
        return List.of("Banana", "Strawberry","Cherry","Raspberry");
    }

    @Bean
    public PersonenService createPersonenService(final PersonRepository repo, @Qualifier("antipathen") final List<String> antipathen) {
        return new PersonenServiceImpl(repo, antipathen);
    }
}
