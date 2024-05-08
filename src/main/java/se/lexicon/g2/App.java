package se.lexicon.g2;

import se.lexicon.g2.data.impl.PeopleDaoImpl;
import se.lexicon.g2.data.impl.TodoItemsDaoImpl;
import se.lexicon.g2.model.Person;
import se.lexicon.g2.model.Todo;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        /*PeopleDaoImpl prelimTest = new PeopleDaoImpl();
        Person testPerson1 = new Person("Test", "Testsson");
        prelimTest.create(testPerson1);*/
        TodoItemsDaoImpl prelimTodoTest = new TodoItemsDaoImpl();
        Todo todoTest = new Todo("Package","Wrap up stuff to move out", LocalDate.of(2024,5,10),false, 1);
        Todo todoTest2 = prelimTodoTest.findById(5);
        todoTest2.setAssigneeId(0);
        todoTest2.setDescription("Finally got it working");
        System.out.println(todoTest2.getAssigneeId());
        //prelimTodoTest.create(todoTest);
        //prelimTodoTest.findAll();
        //System.out.println(prelimTodoTest.findByDoneStatus(false));
        /*prelimTodoTest.create(todoTest2);
        prelimTodoTest.findByUnassignedTodoItems();*/
        prelimTodoTest.update(todoTest2);
    }
}
