package controller.admin;

import database.SettingsDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.EmailUtil;
import manager.SceneManager;



import java.io.File;
import java.util.Optional;

public class SystemSettingsController {

    // SMTP
    @FXML private TextField smtpHostField;
    @FXML private TextField smtpPortField;
    @FXML private TextField smtpLoginField;
    @FXML private PasswordField smtpPasswordField;
    @FXML private Label smtpStatusLabel;

    // Hours
    @FXML private Spinner<Integer> startHourSpinner;
    @FXML private Spinner<Integer> endHourSpinner;

    // Reminders
    @FXML private CheckBox remindersEnabledCheckBox;
    @FXML private Spinner<Integer> reminderIntervalSpinner;

    @FXML
    public void initialize() {
        var settings = SettingsDAO.loadSettings();
        smtpHostField.setText(settings.getSmtpHost());
        smtpPortField.setText(String.valueOf(settings.getSmtpPort()));
        smtpLoginField.setText(settings.getSmtpLogin());
        smtpPasswordField.setText(settings.getSmtpPassword());

        startHourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, settings.getStartHour()));
        endHourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, settings.getEndHour()));

        remindersEnabledCheckBox.setSelected(settings.isRemindersEnabled());
        reminderIntervalSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 72, settings.getReminderInterval()));
    }

    @FXML
    private void handleSave() {
        SettingsDAO.saveSettings(
                smtpHostField.getText(),
                Integer.parseInt(smtpPortField.getText()),
                smtpLoginField.getText(),
                smtpPasswordField.getText(),
                startHourSpinner.getValue(),
                endHourSpinner.getValue(),
                remindersEnabledCheckBox.isSelected(),
                reminderIntervalSpinner.getValue()
        );
        showAlert("Zapisano ustawienia.");
    }

    @FXML
    private void handleTestEmail() {
        boolean ok = EmailUtil.testConnection(
                smtpHostField.getText(),
                Integer.parseInt(smtpPortField.getText()), // 🔧 rzutowanie na int
                smtpLoginField.getText(),
                smtpPasswordField.getText()
        );
        smtpStatusLabel.setText(ok ? "Połączenie OK ✅" : "Błąd połączenia ❌");
    }

    @FXML
    private void handleExportLogs() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Eksportuj logi do CSV");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = chooser.showSaveDialog(new Stage());
        if (file != null) {
            boolean success = SettingsDAO.exportLogsToCSV(file);
            showAlert(success ? "Eksport zakończony sukcesem." : "Błąd eksportu.");
        }
    }

    @FXML
    private void handleClearTestData() {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Czy na pewno chcesz wyczyścić dane testowe?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            SettingsDAO.clearTestData();
            showAlert("Wyczyszczono dane testowe.");
        }
    }

    private void showAlert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK).showAndWait();
    }
    @FXML
    private void handleTestSMTP() {
        handleTestEmail(); // Wywołuje istniejącą metodę
    }

    @FXML
    private void handleSaveSMTP() {
        handleSave(); // Możesz podzielić na osobną logikę jeśli chcesz
    }

    @FXML
    private void handleSaveWorkingHours() {
        // np. tylko część odpowiedzialna za godziny
        SettingsDAO.saveWorkingHours(
                startHourSpinner.getValue(),
                endHourSpinner.getValue()
        );
        showAlert("Zapisano godziny pracy.");
    }

    @FXML
    private void handleClearDemoData() {
        handleClearTestData(); // delegacja
    }

    @FXML
    private void handleSaveReminderSettings() {
        SettingsDAO.saveReminderSettings(
                remindersEnabledCheckBox.isSelected(),
                reminderIntervalSpinner.getValue()
        );
        showAlert("Zapisano przypomnienia.");
    }
    @FXML
    private void handleBack() {
        SceneManager.setScene("/view/ADMIN/dashboard_admin.fxml");
    }

}
