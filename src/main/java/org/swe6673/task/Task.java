package org.swe6673.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task implements Comparable<Task> {
    private String taskName;
    private LocalDate due_date;
    private Boolean isCompleted;
    private List<String> usersInvolved;
    private List<String> comments;

    public Task(String taskName, LocalDate approachingDeadline, Boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
        this.due_date = LocalDate.now();
        this.usersInvolved = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public String getTaskName() {
        return taskName;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    public boolean isOverdue() {
        return LocalDate.now().isAfter(due_date);
    }



    public String getName() {
        return taskName;
    }

    public boolean isCompleted() {
        return false;
    }
    public LocalDate getDueDate() {
        return due_date;
    }
    public void setDueDate(LocalDate dueDate) {
        this.due_date = dueDate;
    }

    public void completeTask() {
    }

    public void printTaskDetails() {
        System.out.println("Task Name: " + taskName);
        System.out.println("Due Date: " + due_date);
    }
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public int compareTo(Task o) {
        return this.taskName.compareTo(o.getName());
    }


    public void updateTask(String newTaskName, LocalDate newDueDate, boolean newIsCompleted) {
        this.taskName = newTaskName;
        this.due_date = newDueDate;
        this.isCompleted = newIsCompleted;
    }

    public void updateTaskName(String newTaskName) {
        this.taskName = newTaskName;
    }

    public void printTaskStatus() {
        System.out.println("Task Name: " + taskName);
        System.out.println("Completed: " + isCompleted);
    }


    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", due_date=" + due_date +
                ", isCompleted=" + isCompleted +
                '}';
    }

    public void setUsersInvolved(List<String> usersInvolved) {
        this.usersInvolved = usersInvolved;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public List<String> getUsersInvolved() {
        return usersInvolved;
    }

    public List<String> getComments() {
        return comments;
    }
}
