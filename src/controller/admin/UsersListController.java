package controller.admin;

import database.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;
import manager.SceneManager;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.io.IOException;

public class UsersListController {

    @FXML private TableView<User> usersTable;
    @FXML private TableColumn<User, Integer> colId;
    @FXML private TableColumn<User, String> colName;
    @FXML private TableColumn<User, String> colEmail;
    @FXML private TableColumn<User, String> colRole;
    @FXML private TableColumn<User, Void> colActions;
    @FXML private TextField searchField;

    private final ObservableList<User> usersList = FXCollections.observableArrayList();
    private FilteredList<User> filteredUsers;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFullName()));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        // ⬇️ Ustawienie ikon dla przycisków w kolumnie akcji
        colActions.setCellFactory(param -> new TableCell<>() {
            private final Button editBtn = new Button();
            private final Button deleteBtn = new Button();

            {
                FontIcon editIcon = new FontIcon(FontAwesomeSolid.EDIT);
                editIcon.setIconSize(16);
                editBtn.setGraphic(editIcon);
                editBtn.getStyleClass().add("action-button");
                editBtn.setOnAction(e -> handleEditUser(getTableView().getItems().get(getIndex())));

                FontIcon deleteIcon = new FontIcon(FontAwesomeSolid.TRASH);
                deleteIcon.setIconSize(16);
                deleteBtn.setGraphic(deleteIcon);
                deleteBtn.getStyleClass().add("action-button");
                deleteBtn.setOnAction(e -> handleDeleteUser(getTableView().getItems().get(getIndex())));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox box = new HBox(8, editBtn, deleteBtn);
                    setGraphic(box);
                }
            }
        });

        loadUsersFromDatabase();
        setupSearch();
    }

    private void loadUsersFromDatabase() {
        usersList.clear();
        usersList.addAll(UserDAO.getAllUsers());
        filteredUsers = new FilteredList<>(usersList, p -> true);
        usersTable.setItems(filteredUsers);
    }

    private void setupSearch() {
        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            filteredUsers.setPredicate(user -> {
                if (newVal == null || newVal.isEmpty()) return true;
                String lower = newVal.toLowerCase();
                return user.getFullName().toLowerCase().contains(lower)
                        || user.getEmail().toLowerCase().contains(lower)
                        || user.getRole().toLowerCase().contains(lower);
            });
        });
    }

    @FXML
    private void handleAddUser() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ADMIN/add_user_modal.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Dodaj użytkownika");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            handleRefresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleEditUser(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ADMIN/edit_user_modal.fxml"));
            Parent root = loader.load();

            EditUserController controller = loader.getController();
            controller.setUser(user);

            Stage stage = new Stage();
            stage.setTitle("Edytuj użytkownika");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            handleRefresh(); // Po edycji
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleDeleteUser(User user) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText("Czy na pewno chcesz usunąć użytkownika " + user.getFullName() + "?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                boolean deleted = UserDAO.deleteUser(user.getId());
                if (deleted) {
                    // ✅ Logowanie usunięcia
                    User current = session.SessionManager.getLoggedInUser();
                    database.LogDAO.log(
                            current.getId(),
                            current.getEmail(),
                            "User",
                            "DELETE",
                            "Usunięto użytkownika: " + user.getUsername()
                    );

                    loadUsersFromDatabase();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Nie udało się usunąć użytkownika.").showAndWait();
                }
            }
        });
    }

    @FXML
    private void handleRefresh() {
        loadUsersFromDatabase();
    }

    @FXML
    private void handleBack() {
        SceneManager.setScene("/view/ADMIN/dashboard_admin.fxml");
    }
}
