import org.junit.jupiter.api.Test;
import org.swe6673.task.Task;
import org.swe6673.taskmanager.TaskManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class CommentTest {

    @Test
    public void testAddCommentToTask() {
        TaskManager taskManager = new TaskManager();
        LocalDate dueDate = LocalDate.now().plusDays(7);
        Task task = new Task("Test Task", dueDate, false);
        taskManager.addTask(task);

        String commentText = "This is a test comment.";
        task.addComment(commentText);

        Task retrievedTask = taskManager.getTaskByName("Test Task");
        assertEquals(1, retrievedTask.getComments().size(), "Expected one comment to be added to the task");
        assertEquals(commentText, retrievedTask.getComments().get(0), "Expected comment text to match");
    }
}
