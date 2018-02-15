package application.controllers;

import application.core.library.field.options.LibraryFieldOptionEnum;
import application.core.library.field.types.FieldTypeEnum;
import application.dto.FieldDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FieldController implements Initializable {


    @FXML
    public TextField name;

    @FXML
    public HBox options;

    @FXML
    public ChoiceBox<String> fieldTypes;

    @FXML
    public Button button;

    private List<String> selectedOpts;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> obsList = FXCollections.observableArrayList(FieldTypeEnum.names());
        fieldTypes.setItems(obsList);
        fieldTypes.setValue(obsList.get(0));
        selectedOpts = new ArrayList<>();
        createBoxes();
    }

    private void createBoxes() {
        for (LibraryFieldOptionEnum opt : LibraryFieldOptionEnum.values()) {
            CheckBox box = new CheckBox(opt.getText());
            box.setText(opt.getText());
            box.setOnAction(event -> boxClicked(box));
            options.getChildren().add(box);
        }
    }

    /**
     * handler for box event
     * @param box clickedBox
     */
    private void boxClicked(CheckBox box) {
        if (box.isSelected()) {
            selectedOpts.add(box.getText());
        } else {
            selectedOpts.removeIf(s -> s.equals(box.getText()));
        }
    }

    /**
     * handler for add button
     */
    public void Add() {


        //create dto form field data
        FieldDTO dto = new FieldDTO(
                selectedOpts,
                name.getText(),
                fieldTypes.getValue()
        );
        //store dto in some local storage?

        //map dto to fxml treeElement

        //move dto to treeView
    }

}
