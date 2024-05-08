package se.lexicon.g2.data;

import se.lexicon.g2.model.Person;

import java.util.Collection;

public interface PeopleDao {
    Person create(Person person);
    Collection<Person> findAll();
    Person findById(int id);
    Collection<Person> findByName(String name);
    Person update(Person person);
    boolean deleteById(int id);
}
