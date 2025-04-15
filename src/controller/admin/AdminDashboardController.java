package controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import manager.SceneManager;
import model.User;
import session.SessionManager;

public class AdminDashboardController {

    @FXML private Label welcomeLabel;

    @FXML
    public void initialize() {
        User currentUser = SessionManager.getLoggedInUser();
        if (currentUser != null) {
            welcomeLabel.setText("Witaj, " + currentUser.getFullName());
        } else {
            welcomeLabel.setText("Błąd: Brak użytkownika.");
        }
    }
    @FXML
    private void handleUsers(ActionEvent event) {
        SceneManager.setScene("/view/ADMIN/users_list.fxml");
    }
    @FXML
    private void handleLogs() {
        SceneManager.setScene("/view/ADMIN/logs_view.fxml");
    }
    @FXML
    private void handleSettings() {
        SceneManager.setScene("/view/ADMIN/system_settings.fxml");
    }
    @FXML
    private void handleLogout() {
        SessionManager.logout();
    }
}
