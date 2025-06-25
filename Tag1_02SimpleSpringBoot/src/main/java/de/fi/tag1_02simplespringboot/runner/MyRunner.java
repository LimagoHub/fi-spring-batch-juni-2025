package de.fi.tag1_02simplespringboot.runner;

import de.fi.tag1_02simplespringboot.demo.Demo;
import de.fi.tag1_02simplespringboot.persistence.PersonRepository;
import de.fi.tag1_02simplespringboot.persistence.entity.PersonEntity;

import de.fi.tag1_02simplespringboot.service.PersonenService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

//@Component

public class MyRunner implements CommandLineRunner {


   private final PersonenService service;

    public MyRunner(final PersonenService service) {
        this.service = service;
    }

    @Override
    public void run(final String... args) throws Exception {


        service.speichern(new PersonEntity(UUID.randomUUID(),"Erika","Mustermann"));
        System.out.println("Hat geklappt!");

    }
}
