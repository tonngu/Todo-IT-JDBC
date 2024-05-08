package se.lexicon.g2.data.impl;

import se.lexicon.g2.data.TodoItemsDao;
import se.lexicon.g2.db.MySQLConnection;
import se.lexicon.g2.model.Person;
import se.lexicon.g2.model.Todo;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TodoItemsDaoImpl implements TodoItemsDao {

    @Override
    public Todo create(Todo todoItem) {
        String query = "insert into todo_item(title, description, deadline, done, assignee_id) values (?, ?, ?, ?, ?)";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, todoItem.getTitle());
            preparedStatement.setString(2, todoItem.getDescription());
            preparedStatement.setDate(3, Date.valueOf(todoItem.getDeadline()));
            preparedStatement.setBoolean(4, todoItem.isDone());
            if (todoItem.getAssigneeId() != 0) {
                preparedStatement.setInt(5, todoItem.getAssigneeId());
            } else {
                preparedStatement.setNull(5, Types.INTEGER);
            }

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Todo item added successfully");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItem;
    }


    @Override
    public Collection<Todo> findAll() {
        Collection<Todo> todoList = new ArrayList<>();

        try (Connection connection = MySQLConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM todo_item")) {

            while (resultSet.next()) {
                int todoId = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                LocalDate deadline = resultSet.getDate(4).toLocalDate();
                boolean done = resultSet.getBoolean(5);
                int assigneeId = resultSet.getInt(6);
                Todo todo = new Todo(todoId, title, description, deadline, done, assigneeId);
                todoList.add(todo);
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: ");
            e.printStackTrace();
        }
        todoList.forEach(System.out::println);
        return todoList;
    }

    @Override
    public Todo findById(int id) {
        Todo todo = null;
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from todo_item where todo_id = ?")) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int todoId = resultSet.getInt(1);
                    String title = resultSet.getString(2);
                    String description = resultSet.getString(3);
                    LocalDate deadline = resultSet.getDate(4).toLocalDate();
                    boolean done = resultSet.getBoolean(5);
                    int assigneeId = resultSet.getInt(6);
                    todo = new Todo(todoId, title, description, deadline, done, assigneeId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todo;
    }

    @Override
    public Collection<Todo> findByDoneStatus(boolean done) {
        Collection<Todo> todoList = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from todo_item where done = ?")) {
            preparedStatement.setBoolean(1, done);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int todoId = resultSet.getInt(1);
                    String title = resultSet.getString(2);
                    String description = resultSet.getString(3);
                    LocalDate deadline = resultSet.getDate(4).toLocalDate();
                    boolean isDone = resultSet.getBoolean(5);
                    int assigneeId = resultSet.getInt(6);
                    Todo todo = new Todo(todoId, title, description, deadline, isDone, assigneeId);
                    todoList.add(todo);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoList;
    }

    @Override
    public Collection<Todo> findByAssignee(int id) {
        Collection<Todo> todoList = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from todo_item where assignee_id = ?")) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int todoId = resultSet.getInt(1);
                    String title = resultSet.getString(2);
                    String description = resultSet.getString(3);
                    LocalDate deadline = resultSet.getDate(4).toLocalDate();
                    boolean isDone = resultSet.getBoolean(5);
                    int assigneeId = resultSet.getInt(6);
                    Todo todo = new Todo(todoId, title, description, deadline, isDone, assigneeId);
                    todoList.add(todo);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoList;
    }

    @Override
    public Collection<Todo> findByAssignee(Person person) {
        Collection<Todo> todoList = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from todo_item where assignee_id = ?")) {
            preparedStatement.setInt(1, person.getPersonId());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int todoId = resultSet.getInt(1);
                    String title = resultSet.getString(2);
                    String description = resultSet.getString(3);
                    LocalDate deadline = resultSet.getDate(4).toLocalDate();
                    boolean isDone = resultSet.getBoolean(5);
                    int assigneeId = resultSet.getInt(6);
                    Todo todo = new Todo(todoId, title, description, deadline, isDone, assigneeId);
                    todoList.add(todo);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoList;
    }

    @Override
    public Collection<Todo> findByUnassignedTodoItems() {
        Collection<Todo> todoList = new ArrayList<>();

        try (Connection connection = MySQLConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM todo_item where assignee_id is null")) {

            while (resultSet.next()) {
                int todoId = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                LocalDate deadline = resultSet.getDate(4).toLocalDate();
                boolean done = resultSet.getBoolean(5);
                int assigneeId = resultSet.getInt(6);
                Todo todo = new Todo(todoId, title, description, deadline, done, assigneeId);
                todoList.add(todo);
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: ");
            e.printStackTrace();
        }
        todoList.forEach(System.out::println);
        return todoList;
    }

    @Override
    public Todo update(Todo todoItem) {
        String query = "update todo_item set title = ?, description = ?, deadline = ?, done = ?, assignee_id = ? where todo_id = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, todoItem.getTitle());
            preparedStatement.setString(2, todoItem.getDescription());
            preparedStatement.setDate(3, Date.valueOf(todoItem.getDeadline()));
            preparedStatement.setBoolean(4, todoItem.isDone());
            if (todoItem.getAssigneeId() != 0) {
                preparedStatement.setInt(5, todoItem.getAssigneeId());
            } else {
                preparedStatement.setNull(5, Types.INTEGER);
            }
            preparedStatement.setInt(6, todoItem.getTodoId());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Todo item updated successfully");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoItem;
    }

    @Override
    public boolean deleteById(int id) {
        boolean deleted = false;

        String query = "delete from todo_item where todo_id = ?";
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Todo item removed from database successfully");
                deleted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }


}
