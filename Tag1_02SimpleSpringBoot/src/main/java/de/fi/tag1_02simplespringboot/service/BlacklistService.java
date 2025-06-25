package de.fi.tag1_02simplespringboot.service;

import de.fi.tag1_02simplespringboot.persistence.entity.PersonEntity;

public interface BlacklistService {

    boolean isBlacklisted(PersonEntity possibleBlacklistedPerson) ;
}
