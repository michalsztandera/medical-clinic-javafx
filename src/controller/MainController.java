package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class MainController {

    @FXML
    private void handlePatients(ActionEvent event) {
        showInfo("Pacjenci", "Tutaj będzie ekran zarządzania pacjentami.");
    }

    @FXML
    private void handleAppointments(ActionEvent event) {
        showInfo("Wizyty", "Tutaj będzie ekran wizyt.");
    }

    @FXML
    private void handlePrescriptions(ActionEvent event) {
        showInfo("Recepty", "Tutaj będzie ekran recept.");
    }

    @FXML
    private void handleExit(ActionEvent event) {
        System.exit(0);
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
