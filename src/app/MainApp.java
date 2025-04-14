package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import manager.SceneManager;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneManager.init(primaryStage);
        SceneManager.setScene("/view/main_view.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
