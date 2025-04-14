package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://45.159.167.89:3306/medical-clinic";
    private static final String USER = "psk_studia";
    private static final String PASSWORD = "qojy3szDhzegvymZeXdpbXPSy ";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
