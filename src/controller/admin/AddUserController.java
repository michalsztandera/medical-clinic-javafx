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

public class AddUserController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<String> roleComboBox;

    private final Map<String, String> roleMap = new HashMap<>();

    @FXML
    public void initialize() {
        roleMap.put("Pacjent", "PATIENT");
        roleMap.put("Lekarz", "DOCTOR");
        roleMap.put("Rejestrator", "REGISTRAR");
        roleMap.put("Administrator", "ADMIN");

        roleComboBox.setItems(FXCollections.observableArrayList(roleMap.keySet()));
    }

    @FXML
    private void handleSave() {
        String first = firstNameField.getText();
        String last = lastNameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String roleDisplay = roleComboBox.getValue();

        if (first.isEmpty() || last.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || roleDisplay == null) {
            showAlert("Uzupełnij wszystkie pola.");
            return;
        }

        String role = roleMap.get(roleDisplay);
        boolean success = UserDAO.addUser(new User(0, username, email, role, first, last), password);
        if (success) {
            User current = SessionManager.getLoggedInUser();
            LogDAO.log(current.getId(), current.getEmail(), "User", "CREATE", "Dodano nowego użytkownika: " + username);
            closeWindow();
        } else {
            showAlert("Błąd podczas zapisu do bazy danych.");
        }
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK);
        alert.showAndWait();
    }

    private void closeWindow() {
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
}
