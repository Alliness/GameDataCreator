package application;

import application.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.*;

public class App {

    private final ExecutorService executor = Executors.newFixedThreadPool(5, r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });


    private static App ourInstance = new App();
    private Stage stage;
    private Scene scene;

    public static App getInstance() {
        return ourInstance;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public MainController getController() {

        FXMLLoader loader = (FXMLLoader) scene.getUserData();
        return loader.getController();
    }
}
