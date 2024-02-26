import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.swe6673.task.Task;
import org.swe6673.taskmanager.TaskManager;

public class TaskManagerTest {

    @Test
    public void taskManagerEmpty() throws Exception{
        TaskManager taskManager = new TaskManager();
        List<Task> tasks = taskManager.tasks();
        assertTrue(tasks.isEmpty(),()->"Task Manager should be empty.");
    }

    @Test
    public void testAddTask() {
        // Test adding a new task
    }

    @Test
    public void testUpdateTask() {
        // Test updating a task
    }

    @Test
    public void testMarkTaskAsCompleted() {
        // Test marking a task as complete
    }

    @Test
    public void testDeleteTask() {
        // Test deleting a task
    }


}
