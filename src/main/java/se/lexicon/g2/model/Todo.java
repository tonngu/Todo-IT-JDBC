package se.lexicon.g2.model;

import java.time.LocalDateTime;

public class Todo {
private int todoId;
private String title;
private String description;
private LocalDateTime deadline;
private boolean done;
private int assigneeId;

//Constructor for fetching from database
    public Todo(int todoId, String title, String description, LocalDateTime deadline, boolean done, int assigneeId) {
        this.todoId = todoId;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
        this.assigneeId = assigneeId;
    }

    //Constructor for adding a TodoItem to database
    public Todo(String title, String description, LocalDateTime deadline, boolean done, int assigneeId) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
        this.assigneeId = assigneeId;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "todoId=" + todoId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", done=" + done +
                ", assigneeId=" + assigneeId +
                '}';
    }
}
