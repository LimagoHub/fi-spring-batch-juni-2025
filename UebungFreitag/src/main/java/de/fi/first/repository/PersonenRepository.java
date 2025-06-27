package de.fi.first.repository;


import de.fi.first.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonenRepository extends CrudRepository<Person,Integer> {
}
