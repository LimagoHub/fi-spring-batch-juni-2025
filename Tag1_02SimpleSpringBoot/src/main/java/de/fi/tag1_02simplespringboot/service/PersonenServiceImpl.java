package de.fi.tag1_02simplespringboot.service;

import de.fi.tag1_02simplespringboot.persistence.PersonRepository;
import de.fi.tag1_02simplespringboot.persistence.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Service
@Transactional(rollbackFor = PersonenServiceException.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class PersonenServiceImpl implements PersonenService {

    private final PersonRepository repo;
    //private final BlacklistService blacklistService;
    private final List<String> antipathen;

    public PersonenServiceImpl(final PersonRepository repo,@Qualifier("antipathen") final List<String> antipathen) {
        this.repo = repo;
        this.antipathen = antipathen;
    }

   /*
        person ist null -> PSE
        person vorname ist null oder weniger als 2 Zeichen ->PSE
        person nachname ist null oder weniger als 2 Zeichen ->PSE
        person vorname ist Attila  ->PSE
        Any Excpetion -> PSE
        pass person to repo
     */

    //@Transactional(rollbackFor = PersonenServiceException.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public void speichern(final PersonEntity person) throws PersonenServiceException {
        try {
            checkPerson(person);
            repo.save(person);
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Ein  Fehler ist aufgetreten", e);
        }
    }

    private void checkPerson(final PersonEntity person) throws PersonenServiceException {
        validatePerson(person);
        businessCheck(person);
    }

    private void businessCheck(final PersonEntity person) throws PersonenServiceException {
        if(antipathen.contains(person.getVorname())) throw new PersonenServiceException("Unerwuenschte Person");
    }

    private static void validatePerson(final PersonEntity person) throws PersonenServiceException {
        if(person == null) throw new PersonenServiceException("Person darf nicht null sein");
        if(person.getVorname() == null || person.getVorname().length() < 2) throw new PersonenServiceException("Vorname zu kurz");
        if(person.getNachname() == null || person.getNachname().length() < 2) throw new PersonenServiceException("Nachname zu kurz");
    }
}
