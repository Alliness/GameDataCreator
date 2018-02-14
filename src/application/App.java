package application;

import javafx.stage.Stage;

public class App {
    private static App ourInstance = new App();
    private Stage stage;

    public static App getInstance() {
        return ourInstance;
    }

    private App() {

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
