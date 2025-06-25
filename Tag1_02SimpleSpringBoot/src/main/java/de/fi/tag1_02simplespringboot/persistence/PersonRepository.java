package de.fi.tag1_02simplespringboot.persistence;

import de.fi.tag1_02simplespringboot.persistence.entity.PersonEntity;
import de.fi.tag1_02simplespringboot.persistence.entity.TinyPerson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonRepository extends CrudRepository<PersonEntity, UUID> {

    Iterable<PersonEntity> findByVorname(String vorname);

    @Query("select p from PersonEntity p")
    Iterable<PersonEntity> xyz();

    @Query("select p.vorname from PersonEntity p")
    Iterable<String> abc();

    @Query("select p.id, p.nachname from PersonEntity p")
    Iterable<Object[]> auswahl();

    @Query("select new de.fi.tag1_02simplespringboot.persistence.entity.TinyPerson(p.id, p.nachname) from PersonEntity p")
    Iterable<TinyPerson> findAllTinies();

    @Query("select p.vorname from PersonEntity p where p.vorname=:vorname")
    Iterable<PersonEntity> fbv(String vorname);
}
