package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root  = FXMLLoader.load(getClass().getResource("resources/fxml/main.fxml"));
        Scene  scene = new Scene(root, 1024, 768); // todo move width and height to config
        scene.getStylesheets().add(getClass().getResource("resources/main.css").toExternalForm());
        primaryStage.setTitle("Json Game Creator");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
        App.getInstance().setStage(primaryStage);
    }
}
