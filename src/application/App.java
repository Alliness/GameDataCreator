package application;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class App {
    private static App ourInstance = new App();
    private Stage stage;
    private Scene scene;

    public static App getInstance() {
        return ourInstance;
    }

    private App() {

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Object getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
