import org.junit.jupiter.api.Test;
import org.swe6673.task.Task;
import org.swe6673.taskmanager.TaskManager;
import org.swe6673.notification.NotificationService;
import org.swe6673.notification.NotificationPreferences;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NotificationTest {

    @Test
    public void testNotificationOnDeadlines() {
        TaskManager taskManager = new TaskManager();
        LocalDate today = LocalDate.now();

        LocalDate approachingDeadline = today.plusDays(2);
        Task approachingTask = new Task("Task with Approaching Deadline", approachingDeadline, false);
        taskManager.addTask(approachingTask);

        NotificationService notificationService = new NotificationService();

        notificationService.checkApproachingDeadlines();

        // Test
        assertTrue(notificationService.hasNotificationFor(approachingTask));
    }

    @Test
    public void testNotificationOnOverdue() {
        TaskManager taskManager = new TaskManager();
        LocalDate today = LocalDate.now();

        LocalDate overdueDeadline = today.minusDays(1);
        Task overdueTask = new Task("Overdue Task", overdueDeadline, false);
        taskManager.addTask(overdueTask);

        NotificationService notificationService = new NotificationService();

        notificationService.checkApproachingDeadlines();

        // Test
        assertTrue(notificationService.hasNotificationFor(overdueTask));
    }

    @Test
    public void testNotificationOnFuture() {
        TaskManager taskManager = new TaskManager();
        LocalDate today = LocalDate.now();

        LocalDate futureDeadline = today.plusMonths(1);
        Task futureTask = new Task("Future Task", futureDeadline, false);
        taskManager.addTask(futureTask);

        NotificationService notificationService = new NotificationService();

        notificationService.checkApproachingDeadlines();

        // Test
        assertTrue(notificationService.hasNotificationFor(futureTask));
    }

    @Test
    public void testNotificationOnComment() {
        TaskManager taskManager = new TaskManager();
        LocalDate today = LocalDate.now();
        LocalDate approachingDeadline = today.plusDays(2);

        Task task = new Task("Task with Approaching Deadline", approachingDeadline, false);
        taskManager.addTask(task);

        NotificationService notificationService = new NotificationService();

        String testComment = "This is a test comment.";
        task.addComment(testComment);

        notificationService.notifyUsersOnComment(task);

        List<String> expectedComments = new ArrayList<>();
        expectedComments.add(testComment);

        List<String> actualComments = task.getComments();

        assertEquals(expectedComments, actualComments, "Expected match");
    }


    @Test
    public void testNotificationPreferences() {
        NotificationService notificationService = new NotificationService();
        String userEmail = "user1@example.com";

        NotificationPreferences initialPreferences = notificationService.getNotificationPreferences(userEmail);

        initialPreferences.setOptInForEmail(true);

        notificationService.updateNotificationPreferences(userEmail, initialPreferences);

        NotificationPreferences updatedPreferences = notificationService.getNotificationPreferences(userEmail);

        assertEquals(true, updatedPreferences.isOptInForEmail(), "Expected to be true");

    }

    @Test
    public void testNotificationPreferencesFalse() {
        NotificationService notificationService = new NotificationService();
        String userEmail = "user2@example.com";

        NotificationPreferences initialPreferences = notificationService.getNotificationPreferences(userEmail);

        initialPreferences.setOptInForEmail(true);

        notificationService.updateNotificationPreferences(userEmail, initialPreferences);

        NotificationPreferences updatedPreferences = notificationService.getNotificationPreferences(userEmail);

        assertEquals(false, updatedPreferences.isOptInForEmail(), "Expected to fail");

    }

    @Test
    //This may be the same as above wanted to showcase changing
    public void testUpdateNotificationPreferences() {
        NotificationService notificationService = new NotificationService();
        String userEmail = "user1@example.com";

        NotificationPreferences initialPreferences = notificationService.getNotificationPreferences(userEmail);

        initialPreferences.setOptInForEmail(true);

        notificationService.updateNotificationPreferences(userEmail, initialPreferences);

        NotificationPreferences updatedPreferences = notificationService.getNotificationPreferences(userEmail);
        assertTrue(updatedPreferences.isOptInForEmail(), "True");

        initialPreferences.setOptInForEmail(false);

        notificationService.updateNotificationPreferences(userEmail, initialPreferences);

        updatedPreferences = notificationService.getNotificationPreferences(userEmail);
        assertFalse(updatedPreferences.isOptInForEmail(), "False");
    }


}
