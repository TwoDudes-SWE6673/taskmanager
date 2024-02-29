import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.swe6673.task.Task;
import org.swe6673.taskmanager.TaskManager;
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
        Task task1 = new Task("Berevenditto",false);
        Task task2 = new Task("Berevenditto",false);
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        List<Task> tasks = taskManager.tasks();
        assertEquals(2, tasks.size(), () -> "Task manager should have two tasks.");
    }

    @Test
    @DisplayName("create a new task")
    public void testAddTask() {
        // Test adding a new task
        Task task = new Task("Task 1",false);
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
        Task task = new Task("Task 1",false);
        taskManager.addTask(task);
        taskManager.updateTask(task, "Updated Task 1");
        List<Task> tasks = taskManager.tasks();
        assertEquals("Updated Task 1", tasks.get(0).getName(), () -> "Task Manager should contain the updated task.");

    }

    @Test
    @DisplayName("mark an existing task as completed")
    public void testMarkTaskAsCompleted() {
        // Test marking a task as complete

        Task task = new Task("Task 1",false);
        taskManager.addTask(task);
        taskManager.markTaskAsCompleted(task);
        List<Task> tasks = taskManager.tasks();
        assertEquals(true, tasks.get(0).isCompleted(), () -> "Task should be marked as completed.");
    }


    @Test
    @DisplayName("get all completed tasks")
    public void testGetAllCompletedTasks() {

        Task task1 = new Task("Task 1",false);
        Task task2 = new Task("Task 2",false);
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

        Task task1 = new Task("Task 1", false);
        Task task2 = new Task("Task 2", false);
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
        Task task = new Task("Task 1", false);
        taskManager.addTask(task);
        taskManager.deleteTask(task);
        List<Task> tasks = taskManager.tasks();
        assertEquals(0, tasks.size(), () -> "Task Manager should be empty after deleting the task.");
    }

    @Test
    @DisplayName("get all incomplete tasks")
    public void testGetAllIncompleteTasks() {

        Task task1 = new Task("Task 1",true);
        Task task2 = new Task("Task 2",false);
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

        Task task = new Task("Task 1",false);
        taskManager.addTask();
        List<Task> tasks = taskManager.tasks();
        assertTrue(tasks.isEmpty(), () -> "Task manager should be empty.");
    }


    @Test
    @DisplayName("ensure task is immutable for client")
    void tasksIsImmutableForClient() {
        Task task1 = new Task("Round One Lifting",false);
        taskManager.addTask(task1);
        List<Task> tasks = taskManager.tasks();
        try {
            tasks.add(new Task("Jumping Jacks", false));
            fail(() -> "Should not be able to add tasks to Manager");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException, () -> "Should throw UnsupportedOperationException.");
        }
    }

    @Test
    @DisplayName("arrange task by title")
    void ManagerArrangedByTitle() {
        taskManager.addTask(new Task("One",false),new Task("Two", false), new Task("Five",false));
        System.out.println(taskManager.toString());
        List<Task> tasks = taskManager.arrange();
        assertEquals(Arrays.asList(tasks.get(0),tasks.get(1),tasks.get(2)), tasks, () -> "Tasks in a manager should be arranged alphabetically by  title");
        System.out.println(tasks.toString());
    }


}
