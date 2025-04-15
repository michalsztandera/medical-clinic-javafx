package model;

public class LogEntry {
    private int id;
    private int userId;
    private String userEmail;
    private String target;
    private String action;
    private String description;
    private String timestamp;

    public LogEntry(int id, int userId, String userEmail, String target, String action, String description, String timestamp) {
        this.id = id;
        this.userId = userId;
        this.userEmail = userEmail;
        this.target = target;
        this.action = action;
        this.description = description;
        this.timestamp = timestamp;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getTarget() {
        return target;
    }

    public String getAction() {
        return action;
    }

    public String getDescription() {
        return description;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
