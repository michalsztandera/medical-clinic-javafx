package controller.admin;

import database.LogDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.LogEntry;
import manager.SceneManager;

import java.util.List;

public class LogsViewController {

    @FXML private TableView<LogEntry> logsTable;
    @FXML private TableColumn<LogEntry, String> colUser;
    @FXML private TableColumn<LogEntry, String> colAction;
    @FXML private TableColumn<LogEntry, String> colTarget;
    @FXML private TableColumn<LogEntry, String> colMessage;
    @FXML private TableColumn<LogEntry, String> colTime;

    @FXML
    public void initialize() {
        colUser.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));
        colTarget.setCellValueFactory(new PropertyValueFactory<>("target"));
        colMessage.setCellValueFactory(new PropertyValueFactory<>("description"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("timestamp"));

        loadLogs();
    }

    private void loadLogs() {
        List<LogEntry> logs = LogDAO.getAllLogs();
        logsTable.getItems().setAll(logs);
    }

    @FXML
    private void handleBack() {
        SceneManager.setScene("/view/ADMIN/dashboard_admin.fxml");
    }
}
