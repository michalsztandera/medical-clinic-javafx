package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PatientFormController {

    @FXML private TextField nameField;
    @FXML private TextField surnameField;
    @FXML private TextField dobField;
    @FXML private TextArea historyField;

    @FXML
    private void handleSave() {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String dob = dobField.getText();
        String history = historyField.getText();

        if (name.isEmpty() || surname.isEmpty() || dob.isEmpty()) {
            showAlert("All fields except history are required.");
            return;
        }

        System.out.println("✅ Saved Patient: " + name + " " + surname + " | DOB: " + dob);
        System.out.println("🩺 History: " + history);

        showAlert("Patient saved successfully!");
        clearForm();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void clearForm() {
        nameField.clear();
        surnameField.clear();
        dobField.clear();
        historyField.clear();
    }
}
