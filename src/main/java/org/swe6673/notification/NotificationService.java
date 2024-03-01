package org.swe6673.notification;

import org.swe6673.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationService {

    private Map<String, List<Task>> reminders;

    public NotificationService() {
        this.reminders = new HashMap<>();
    }
    public void checkApproachingDeadlines() {
        // TODO
    }

    public boolean hasNotificationFor(Task task) {
        // TODO
        return false;
    }

    public void notifyUsersOnComment(Task task) {
        List<String> usersInvolved = task.getUsersInvolved();
        List<String> comments = task.getComments();

        for (String user : usersInvolved) {
            // TODO send notifications
            // Simulation of new comment
            System.out.println("Notification sent to user: " + user + " for task: " + task.getTaskName());
            System.out.println("New comment: " + comments.get(comments.size() - 1));
        }
    }

    public NotificationPreferences getNotificationPreferences(String userEmail) {
        // TODO
        // test
        return new NotificationPreferences();
    }

    public void updateNotificationPreferences(String userEmail, NotificationPreferences preferences) {
        // TODO
    }
}
