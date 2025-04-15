package model;

public class Settings {
    private String smtpHost;
    private int smtpPort;
    private String smtpLogin;
    private String smtpPassword;
    private int startHour;
    private int endHour;
    private boolean remindersEnabled;
    private int reminderInterval;

    public Settings() {
        this.smtpHost = "";
        this.smtpPort = 587;
        this.smtpLogin = "";
        this.smtpPassword = "";
        this.startHour = 8;
        this.endHour = 16;
        this.remindersEnabled = true;
        this.reminderInterval = 24;
    }

    public Settings(String smtpHost, int smtpPort, String smtpLogin, String smtpPassword,
                    int startHour, int endHour, boolean remindersEnabled, int reminderInterval) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.smtpLogin = smtpLogin;
        this.smtpPassword = smtpPassword;
        this.startHour = startHour;
        this.endHour = endHour;
        this.remindersEnabled = remindersEnabled;
        this.reminderInterval = reminderInterval;
    }

    public String getSmtpHost() { return smtpHost; }
    public int getSmtpPort() { return smtpPort; }
    public String getSmtpLogin() { return smtpLogin; }
    public String getSmtpPassword() { return smtpPassword; }
    public int getStartHour() { return startHour; }
    public int getEndHour() { return endHour; }
    public boolean isRemindersEnabled() { return remindersEnabled; }
    public int getReminderInterval() { return reminderInterval; }
}
