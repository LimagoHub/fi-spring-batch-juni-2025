package de.fi.tag1_02simplespringboot.service;

import de.fi.tag1_02simplespringboot.persistence.entity.PersonEntity;

public interface PersonenService {

    void speichern(PersonEntity person) throws PersonenServiceException;
}
