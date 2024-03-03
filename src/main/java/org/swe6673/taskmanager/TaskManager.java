package org.swe6673.taskmanager;

import org.swe6673.task.Task;

import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new LinkedList<>();
    }

    public Task getTaskByName(String task) {
        for (Task t : tasks) {
            if (t.getName().equals(task)) {
                return t;
            }
        }
        return null;
    }

    public void addTask(Task... tasksToAdd) {
        Arrays.stream(tasksToAdd).forEach(task -> tasks.add(task));
    }
    public void updateTask(Task task, String s) {
    }

    public List<Task> tasks() {
//        return tasks;
        return Collections.unmodifiableList(tasks);
    }

    public void markTaskAsCompleted(Task task) {
    }

    public void deleteTask(Task task) {
        this.tasks.remove(task);
    }

    public List<Task> getCompletedTasks() {
        return Collections.emptyList();
    }

    public List<Task> getIncompleteTasks() {
        List<Task> incompleteTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                incompleteTasks.add(task);
            }
        }
        return incompleteTasks;
    }

    public List<Task> arrange() {
        return tasks.stream().sorted().collect(Collectors.toList());
    }

    public List<Task> getOverdueTasks() {
        List<Task> overdueTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isOverdue()) {
                overdueTasks.add(task);
            }
        }
        return overdueTasks;
    }

    @Override
    public String toString() {
        return "TaskManager{" +
                "tasks=" + tasks +
                '}';
    }
}
