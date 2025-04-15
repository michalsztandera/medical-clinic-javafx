package app;

import javafx.application.Application;
import javafx.stage.Stage;
import manager.SceneManager;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        SceneManager.init(primaryStage);
        SceneManager.setScene("/view/login.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
