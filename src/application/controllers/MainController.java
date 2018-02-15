package application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public MenuBar menuBar;


    @FXML
    TreeView tree;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
