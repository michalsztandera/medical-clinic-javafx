package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import manager.SceneManager;
import manager.DatabaseManager;

public class MainController {

    // 📦 Nawigacja
    @FXML
    private void handlePatients(ActionEvent event) {
        SceneManager.setScene("/view/patient_form.fxml");
    }

    @FXML
    private void handleAppointments(ActionEvent event) {
        System.out.println("📅 Wizyty - do zrobienia");
    }

    @FXML
    private void handlePrescriptions(ActionEvent event) {
        System.out.println("💊 Recepty - do zrobienia");
    }

    @FXML
    private void handleExit(ActionEvent event) {
        System.exit(0);
    }

    // 🌐 Status połączenia
    @FXML private Label connectionStatus;
    @FXML private Label connectionIcon;

    @FXML
    public void initialize() {
        checkConnection();
    }

    private void checkConnection() {
        new Thread(() -> {
            try {
                Thread.sleep(500); // pozwól JavaFX się załadować
                boolean connected = !DatabaseManager.getConnection().isClosed();
                updateConnectionUI(connected);
            } catch (Exception e) {
                updateConnectionUI(false);
            }
        }).start();
    }

    private void updateConnectionUI(boolean connected) {
        Platform.runLater(() -> {
            if (connected) {
                connectionStatus.setText("Połączono z serwerem");
                connectionIcon.setText("✅");
            } else {
                connectionStatus.setText("Brak połączenia z serwerem");
                connectionIcon.setText("❌");
            }
        });
    }
}
