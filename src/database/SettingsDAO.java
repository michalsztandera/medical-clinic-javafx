package database;

import manager.DatabaseManager;
import model.Settings;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.io.File;

public class SettingsDAO {

    public static Settings loadSettings() {
        String query = "SELECT * FROM settings LIMIT 1";

        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                return new Settings(
                        rs.getString("smtp_host"),
                        rs.getInt("smtp_port"),
                        rs.getString("smtp_username"),
                        rs.getString("smtp_password"),
                        rs.getTime("clinic_open_hour").toLocalTime().getHour(),
                        rs.getTime("clinic_close_hour").toLocalTime().getHour(),
                        rs.getBoolean("reminders_enabled"),
                        rs.getInt("reminder_interval_hours")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Settings(); // Zwraca domyślne jeśli brak
    }

    public static void saveSettings(String host, int port, String login, String password,
                                    int startHour, int endHour, boolean reminders, int interval) {
        String query = "UPDATE settings SET smtp_host=?, smtp_port=?, smtp_username=?, smtp_password=?, " +
                "clinic_open_hour=?, clinic_close_hour=?, reminders_enabled=?, reminder_interval_hours=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, host);
            stmt.setInt(2, port);
            stmt.setString(3, login);
            stmt.setString(4, password);
            stmt.setTime(5, Time.valueOf(startHour + ":00:00"));
            stmt.setTime(6, Time.valueOf(endHour + ":00:00"));
            stmt.setBoolean(7, reminders);
            stmt.setInt(8, interval);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean exportLogsToCSV(File file) {
        String query = "SELECT * FROM logs ORDER BY timestamp DESC";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query);
             FileWriter writer = new FileWriter(file)) {

            writer.write("ID,UserID,Email,Entity,Action,Activity,Timestamp\n");

            while (rs.next()) {
                writer.write(rs.getInt("id") + "," +
                        rs.getInt("user_id") + "," +
                        rs.getString("email") + "," +
                        rs.getString("entity") + "," +
                        rs.getString("action") + "," +
                        "\"" + rs.getString("activity").replace("\"", "'") + "\"," +
                        rs.getTimestamp("timestamp") + "\n");
            }

            return true;

        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void clearTestData() {
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("DELETE FROM logs WHERE email LIKE '%test%' OR activity LIKE '%test%'");
            stmt.executeUpdate("DELETE FROM users WHERE email LIKE '%test%' OR username LIKE '%test%'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void saveWorkingHours(int startHour, int endHour) {
        String query = "UPDATE settings SET clinic_open_hour=?, clinic_close_hour=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setTime(1, Time.valueOf(startHour + ":00:00"));
            stmt.setTime(2, Time.valueOf(endHour + ":00:00"));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveReminderSettings(boolean enabled, int interval) {
        String query = "UPDATE settings SET reminders_enabled=?, reminder_interval_hours=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setBoolean(1, enabled);
            stmt.setInt(2, interval);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
