package de.fi.tag1_02simplespringboot.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {
    @Override
    public void run(final String... args) throws Exception {
        System.out.println("Hallo Welt");
    }
}
