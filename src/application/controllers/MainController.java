package application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    @FXML
    private Pane main;

    @FXML
    private TreeController treeController;

    @FXML
    private FieldController fieldController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        treeController.initializeParent(this);
        fieldController.initializeParent(this);
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
}
