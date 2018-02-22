package application.controllers;

import application.core.library.field.options.LibraryFieldOptionEnum;
import application.core.library.field.types.FieldTypeEnum;
import application.dto.FieldDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class FieldController {


    @FXML
    public TextField name;

    @FXML
    public HBox options;

    @FXML
    public ChoiceBox<FieldTypeEnum> fieldTypes;

    @FXML
    public Button button;

    @FXML
    public Pane field;

    private List<LibraryFieldOptionEnum> selectedOpts;

    private MainController main;

    @FXML
    private void initialize() {
        ObservableList<FieldTypeEnum> obsList = FXCollections.observableArrayList(FieldTypeEnum.values());
        fieldTypes.setItems(obsList);
        fieldTypes.setValue(FieldTypeEnum.EMPTY);
        selectedOpts = new ArrayList<>();
        createBoxes();
        button.setAlignment(Pos.CENTER_RIGHT);

    }

    void initializeParent(MainController mainController) {
        this.main = mainController;
        field.prefWidthProperty().bind(mainController.getPane().widthProperty());
    }

    private void createBoxes() {
        for (LibraryFieldOptionEnum opt : LibraryFieldOptionEnum.values()) {
            CheckBox box = new CheckBox(opt.getText());
            box.setText(opt.getText());
            box.setOnAction(event -> boxClicked(box));
            box.setMinWidth(40);
            options.getChildren().add(box);
        }
    }

    /**
     * handler for box event
     *
     * @param box clickedBox
     */
    private void boxClicked(CheckBox box) {
        if (box.isSelected()) {
            selectedOpts.add(LibraryFieldOptionEnum.getByText(box.getText()));
        } else {
            selectedOpts.removeIf(s -> s.equals(LibraryFieldOptionEnum.getByText(box.getText())));
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

        if (dto.getName().equals("")) {
            System.out.println("Empty Field Name");
            return;
            //todo add notice popup;
        }
        //store dto in some local storage?

        //validate @isReference


        //map dto to fxml treeElement
        main.getTreeController().addItem(dto);
        //move dto to treeView
    }
}
