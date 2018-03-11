package application.controllers.structureCreator;

import application.core.library.field.options.LibraryFieldOptionEnum;
import application.core.library.field.types.FieldTypeEnum;
import application.core.utils.Dir;
import application.core.utils.FReader;
import application.dto.FieldDTO;
import application.dto.Serializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FieldController {


    @FXML
    public TextField name;

    @FXML
    public HBox options;

    @FXML
    public ChoiceBox<Object> fieldTypes;

    @FXML
    public Button button;

    @FXML
    public Pane field;

    private List<LibraryFieldOptionEnum> selectedOpts;

    private MainController              main;
    private HashMap<String, JSONObject> refs;

    @FXML
    private void initialize() {
        ObservableList<Object> obsList = FXCollections.observableArrayList(FieldTypeEnum.values());
        // append references

        refs = FReader.readAll(Dir.LIBS);

        refs.forEach((s, jsonObject) -> obsList.add(s));
        fieldTypes.setItems(obsList);
        fieldTypes.setValue(FieldTypeEnum.OBJECT);

        name.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                Add();
            }
        });
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
    @FXML
    public void Add() {
        try {
            FieldDTO dto = new FieldDTO(
                    selectedOpts,
                    name.getText(),
                    (FieldTypeEnum) fieldTypes.getValue()
            );

            if (dto.getName().equals("")) {
                main.printMessage("Empty field name");
                return;
            }
            main.getTreeController().addItem(dto, true);
        } catch (ClassCastException e) {
            if (main.getTreeController().tree.getRoot() == null) {
                main.printMessage("Empty root item");
                return;
            }
            String     parentName = (String) fieldTypes.getValue();
            JSONObject json       = refs.get(parentName);
            FieldDTO   parentDTO  = new FieldDTO(null, parentName, FieldTypeEnum.REFERENCE);
            parentDTO.setRef(parentName);

            main.getTreeController().addItem(parentDTO, false);

            // iterate object to set children
            for (String key : json.keySet()) {
                referenceTreeAppender(json.getJSONObject(key));
            }
        }
        //create dto form field dataCreator


        //store dto in some local storage?

        //validate @isReference


        //map dto to fxml treeElement
    }

    private void referenceTreeAppender(JSONObject object) {
        for (String s : object.keySet()) {
            if (s.equals("$params")) {
                JSONObject             obj  = object.getJSONObject(s);
                FieldDTO               dto  = Serializable.deserialize(obj, FieldDTO.class);
                main.getTreeController().addItem(dto, false);
            } else {
                referenceTreeAppender(object.getJSONObject(s));
            }
        }
    }


}
