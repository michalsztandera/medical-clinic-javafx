package controller.system;

import database.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.User;
import manager.SceneManager;
import session.SessionManager;


public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    private void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        User user = UserDAO.authenticate(email, password);

        if (user != null) {
            SessionManager.setLoggedInUser(user);

            switch (user.getRole()) {
                case "PATIENT":
                    SceneManager.setScene("/view/PATIENT/dashboard_patient.fxml");
                    break;
                case "DOCTOR":
                    SceneManager.setScene("/view/DOCTOR/dashboard_doctor.fxml");
                    break;
                case "REGISTRAR":
                    SceneManager.setScene("/view/REGISTRAR/dashboard_registrar.fxml");
                    break;
                case "ADMIN":
                    SceneManager.setScene("/view/ADMIN/dashboard_admin.fxml");
                    break;
                default:
                    showError("Nieznana rola użytkownika.");
            }
        } else {
            showError("Niepoprawny email lub hasło.");
        }
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }
}
