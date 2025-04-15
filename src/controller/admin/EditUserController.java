package controller.admin;

import database.LogDAO;
import database.UserDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;
import session.SessionManager;

import java.util.HashMap;
import java.util.Map;

public class EditUserController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField usernameField;
    @FXML private ComboBox<String> roleComboBox;

    private User user;

    private final Map<String, String> roleMap = new HashMap<>();
    private final Map<String, String> reverseMap = new HashMap<>();

    @FXML
    public void initialize() {
        roleMap.put("Pacjent", "PATIENT");
        roleMap.put("Lekarz", "DOCTOR");
        roleMap.put("Rejestrator", "REGISTRAR");
        roleMap.put("Administrator", "ADMIN");

        // odwrotna mapa
        for (Map.Entry<String, String> entry : roleMap.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }

        roleComboBox.setItems(FXCollections.observableArrayList(roleMap.keySet()));
    }

    public void setUser(User user) {
        this.user = user;

        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        emailField.setText(user.getEmail());
        usernameField.setText(user.getUsername());
        roleComboBox.setValue(reverseMap.get(user.getRole()));
    }

    @FXML
    private void handleSave() {
        user.setFirstName(firstNameField.getText());
        user.setLastName(lastNameField.getText());
        user.setEmail(emailField.getText());
        user.setUsername(usernameField.getText());
        user.setRole(roleMap.get(roleComboBox.getValue()));

        boolean updated = UserDAO.updateUser(user);
        if (updated) {
            User current = SessionManager.getLoggedInUser();
            LogDAO.log(current.getId(), current.getEmail(), "User", "UPDATE", "Zmieniono dane użytkownika: " + user.getUsername());
            close();
        } else {
            showAlert("Nie udało się zapisać zmian.");
        }
    }

    @FXML
    private void handleCancel() {
        close();
    }

    private void close() {
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String msg) {
        new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK).showAndWait();
    }
}
