package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://45.159.167.89:3306/medical-clinic?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "psk_studia";
    private static final String PASSWORD = "qojy3szDhzegvymZeXdpbXPSy";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            System.out.println("🔄 Próbuję połączyć się z bazą danych...");
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Połączenie z bazą danych udane!");
            } catch (SQLException e) {
                System.out.println("❌ Błąd podczas łączenia z bazą danych:");
                e.printStackTrace();
                throw e;
            }
        }
        return connection;
    }
}
