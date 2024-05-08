package se.lexicon.g2.data;

import se.lexicon.g2.model.Person;
import se.lexicon.g2.model.Todo;

import java.util.Collection;

public interface TodoItemsDao {
    Todo create(Todo todoItem);
    Collection<Todo> findAll();
    Todo findById(int id);
    Collection<Todo> findByDoneStatus(boolean done);
    Collection<Todo> findByAssignee(int id);
    Collection<Todo> findByAssignee(Person person);
    Collection<Todo> findByUnassignedTodoItems();
    Todo update(Todo todoItem);
    boolean deleteById(int id);
}
