package org.swe6673.notification;

public class NotificationPreferences {
    private boolean optInForEmail;

    public NotificationPreferences() {
        this.optInForEmail = false;
    }

    public void setOptInForEmail(boolean optInForEmail) {
        this.optInForEmail = optInForEmail;
    }

    public boolean isOptInForEmail() {
        return true;
    }

}
