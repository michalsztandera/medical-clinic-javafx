package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import manager.DatabaseManager;
import manager.SceneManager;

public class MainController {

    @FXML private Label connectionStatus;
    @FXML private Label connectionIcon;

    @FXML
    public void initialize() {
        new Thread(this::checkConnection).start();
    }

    private void checkConnection() {
        try {
            boolean connected = !DatabaseManager.getConnection().isClosed();
            Platform.runLater(() -> updateConnectionUI(connected));
        } catch (Exception e) {
            Platform.runLater(() -> updateConnectionUI(false));
        }
    }

    private void updateConnectionUI(boolean connected) {
        if (connected) {
            connectionStatus.setText("Połączono z bazą danych");
            connectionIcon.setText("✅");
        } else {
            connectionStatus.setText("Brak połączenia z bazą danych");
            connectionIcon.setText("❌");
        }
    }

    @FXML
    private void patient_list(ActionEvent event) {
        SceneManager.setScene("/view/patient_view.fxml");
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
}
