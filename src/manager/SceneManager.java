package manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private static Stage stage;

    public static void init(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Medical Clinic System");
        stage.setFullScreen(true);
        stage.setMaximized(true);
    }

    public static void setScene(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(SceneManager.class.getResource(fxmlPath));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(SceneManager.class.getResource("/style/style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
