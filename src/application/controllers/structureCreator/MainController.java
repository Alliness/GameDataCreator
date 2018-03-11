package application.controllers.structureCreator;

import application.App;
import application.core.utils.Dir;
import application.core.utils.FWriter;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    @FXML
    public AnchorPane leftPane;

    @FXML
    public AnchorPane rightPane;

    @FXML
    public TextArea viewArea;

    @FXML
    public Button saveButton;

    @FXML
    public TextField savePath;

    @FXML
    private Label alertMessage;

    @FXML
    private Pane main;

    @FXML
    private Label label;

    @FXML
    private SplitPane split;

    @FXML
    private TreeController treeController;

    @FXML
    private FieldController fieldController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        split.prefWidthProperty().bind(main.widthProperty());
        split.prefHeightProperty().bind(main.heightProperty());
        label.prefWidthProperty().bind(main.widthProperty());
        label.alignmentProperty().set(Pos.CENTER);

        treeController.initializeParent(this);
        fieldController.initializeParent(this);

        treeController.tree.prefWidthProperty().bind(leftPane.widthProperty());
        treeController.tree.prefHeightProperty().bind(main.heightProperty());


        viewArea.setEditable(false);

        saveButton.setOnMouseClicked(event -> {
            saveFileProcess();
        });
    }

    public void printMessage(String message) {
        alertMessage.setText(message);
        alertMessage.setTextFill(Color.web("#ff4d4d"));
        FadeTransition fader = createFader(alertMessage);
        fader.play();
    }

    private FadeTransition createFader(Node node) {
        FadeTransition fade = new FadeTransition(Duration.seconds(3), node);
        fade.setFromValue(1);
        fade.setToValue(0);

        return fade;
    }

    private void saveFileProcess() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save LibraryFile");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json file(*.json)", "*.json"));
        try{
            chooser.setInitialFileName(treeController.getRootName() + ".json");
            chooser.setInitialDirectory(new File(Dir.LIBS));
            File file = chooser.showSaveDialog(App.getInstance().getStage());
            if (file != null) {
                FWriter.save(getTreeController().getSkeleton().toString(), file);
            }
        }catch (NullPointerException e){
            printMessage("No object to save!");
        }
    }

    public void setViewText(String text){
        viewArea.setText(text);
    }

    public Pane getPane() {
        return main;
    }

    public TreeController getTreeController() {
        return treeController;
    }

    public FieldController getFieldController() {
        return fieldController;
    }


    public void fileName(String name) {
        savePath.setText(Dir.LIBS + "/" + name + ".json");
    }


}
