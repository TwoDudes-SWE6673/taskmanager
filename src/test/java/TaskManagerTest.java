import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.swe6673.task.Task;
import org.swe6673.taskmanager.TaskManager;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("<= Task Manager Specification =>")
public class TaskManagerTest {

    private TaskManager taskManager;

    private TaskManagerTest(TestInfo testInfo) {
        System.out.println("Working on test " + testInfo.getDisplayName());
    }

    @BeforeEach
    void init() throws Exception {
        taskManager = new TaskManager();
    }

    @Test
    @DisplayName("is empty before adding tasks ")
    public void taskManagerEmpty() throws Exception {
        List<Task> tasks = taskManager.tasks();
        assertTrue(tasks.isEmpty(), () -> "Task Manager should be empty.");
    }

    @Test
    @DisplayName("checks to verify the amount of tasks in manager if added")
    void taskManagerContainsNumberOfTasksAdded() {
        LocalDate approachingDeadline = null;
        Task task1 = new Task("Berevenditto", approachingDeadline, false);
        Task task2 = new Task("Berevenditto", approachingDeadline, false);
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        List<Task> tasks = taskManager.tasks();
        assertEquals(2, tasks.size(), () -> "Task manager should have two tasks.");
    }

    @Test
    @DisplayName("create a new task")
    public void testAddTask() {
        // Test adding a new task
        LocalDate approachingDeadline = null;
        Task task = new Task("Task 1", approachingDeadline, false);
        taskManager.addTask(task);
        List<Task> tasks = taskManager.tasks();
        assertEquals(1, tasks.size(), () -> "Task Manager should contain 1 task.");
        assertEquals(task, tasks.get(0), () -> "Task Manager should contain the added task.");
    }

    @Test
    @DisplayName("update an existing task")
    public void testUpdateTask() {
        // Test updating a task
        TaskManager taskManager = new TaskManager();
        LocalDate approachingDeadline = null;
        Task task = new Task("Task 1", approachingDeadline, false);
        taskManager.addTask(task);
        taskManager.updateTask(task, "Updated Task 1");
        List<Task> tasks = taskManager.tasks();
        assertEquals("Updated Task 1", tasks.get(0).getName(), () -> "Task Manager should contain the updated task.");

    }

    @Test
    @DisplayName("mark an existing task as completed")
    public void testMarkTaskAsCompleted() {
        // Test marking a task as complete

        LocalDate approachingDeadline = null;
        Task task = new Task("Task 1", approachingDeadline, false);
        taskManager.addTask(task);
        taskManager.markTaskAsCompleted(task);
        List<Task> tasks = taskManager.tasks();
        assertEquals(true, tasks.get(0).isCompleted(), () -> "Task should be marked as completed.");
    }


    @Test
    @DisplayName("get all completed tasks")
    public void testGetAllCompletedTasks() {

        LocalDate approachingDeadline = null;
        Task task1 = new Task("Task 1", approachingDeadline, false);
        Task task2 = new Task("Task 2", approachingDeadline, false);
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.markTaskAsCompleted(task1);
        List<Task> completedTasks = taskManager.getCompletedTasks();
        assertEquals(1, completedTasks.size(), () -> "Task Manager should contain 1 completed task.");
        assertTrue(completedTasks.contains(task1), () -> "Task Manager should contain task1 as completed.");
        assertFalse(completedTasks.contains(task2), () -> "Task Manager should not contain task2 as completed.");
    }

    @Test
    @DisplayName("get all tasks")
    public void testGetAllTasks() {

        LocalDate approachingDeadline = null;
        Task task1 = new Task("Task 1", approachingDeadline, false);
        Task task2 = new Task("Task 2", approachingDeadline, false);
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        List<Task> tasks = taskManager.tasks();
        assertEquals(2, tasks.size(), () -> "Task Manager should contain 2 tasks.");
        assertTrue(tasks.contains(task1), () -> "Task Manager should contain task1.");
        assertTrue(tasks.contains(task2), () -> "Task Manager should contain task2.");
    }

    @Test
    public void testDeleteTask() {
        // Test deleting a task
        LocalDate approachingDeadline = null;
        Task task = new Task("Task 1", approachingDeadline, false);
        taskManager.addTask(task);
        taskManager.deleteTask(task);
        List<Task> tasks = taskManager.tasks();
        assertEquals(0, tasks.size(), () -> "Task Manager should be empty after deleting the task.");
    }

    @Test
    @DisplayName("get all incomplete tasks")
    public void testGetAllIncompleteTasks() {

        LocalDate approachingDeadline = null;
        Task task1 = new Task("Task 1", approachingDeadline, true);
        Task task2 = new Task("Task 2", approachingDeadline, false);
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.markTaskAsCompleted(task1);
        List<Task> incompleteTasks = taskManager.getIncompleteTasks();
        assertEquals(1, incompleteTasks.size(), () -> "Task Manager should contain 1 incomplete task.");
        assertFalse(incompleteTasks.contains(task1), () -> "Task Manager should not contain task1 as incomplete.");
        assertTrue(incompleteTasks.contains(task2), () -> "Task Manager should contain task2 as incomplete.");
    }

    @Test
    @DisplayName("if add is called without parameters return empty List")
    public void emptyTaskManagerWhenAddIsCalledWithoutTasks() {

        LocalDate approachingDeadline = null;
        Task task = new Task("Task 1", approachingDeadline, false);
        taskManager.addTask();
        List<Task> tasks = taskManager.tasks();
        assertTrue(tasks.isEmpty(), () -> "Task manager should be empty.");
    }


    @Test
    @DisplayName("ensure task is immutable for client")
    void tasksIsImmutableForClient() {
        LocalDate approachingDeadline = null;
        Task task1 = new Task("Round One Lifting", approachingDeadline, false);
        taskManager.addTask(task1);
        List<Task> tasks = taskManager.tasks();
        try {
            tasks.add(new Task("Jumping Jacks", approachingDeadline, false));
            fail(() -> "Should not be able to add tasks to Manager");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException, () -> "Should throw UnsupportedOperationException.");
        }
    }

    @Test
    @DisplayName("arrange task by title")
    void ManagerArrangedByTitle() {
        LocalDate approachingDeadline = null;
        taskManager.addTask(new Task("One", approachingDeadline, false),new Task("Two", approachingDeadline, false), new Task("Five", approachingDeadline, false));
        System.out.println(taskManager.toString());
        List<Task> tasks = taskManager.arrange();
        assertEquals(Arrays.asList(tasks.get(0),tasks.get(1),tasks.get(2)), tasks, () -> "Tasks in a manager should be arranged alphabetically by  title");
        System.out.println(tasks.toString());
    }


}
