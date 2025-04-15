package database;

import manager.DatabaseManager;
import model.LogEntry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {

    public static void log(int userId, String email, String entity, String action, String activity) {
        String query = "INSERT INTO logs (user_id, email, entity, action, activity) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setString(2, email);
            stmt.setString(3, entity);
            stmt.setString(4, action);
            stmt.setString(5, activity);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<LogEntry> getAllLogs() {
        List<LogEntry> logs = new ArrayList<>();
        String query = "SELECT id, user_id, email, entity, action, activity, timestamp FROM logs ORDER BY timestamp DESC";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                logs.add(new LogEntry(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getString("entity"),
                        rs.getString("action"),
                        rs.getString("activity"),
                        rs.getString("timestamp") // Timestamp jako String
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logs;
    }
}
