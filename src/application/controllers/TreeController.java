package application.controllers;

import application.core.library.field.types.FieldTypeEnum;
import application.core.library.tree.JsonTreeItem;
import application.dto.FieldDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class TreeController {

    private MainController main;

    @FXML
    public TreeView<JsonTreeItem> tree;

    @FXML
    private void initialize() {
        TreeItem<JsonTreeItem> root = new TreeItem<>(
                new JsonTreeItem(
                        new FieldDTO(null, "name", FieldTypeEnum.REFERENCE)
                )); // todo use constructor text input
        root.setExpanded(true);
        tree.setRoot(root);
    }

    void initializeParent(MainController mainController) {
        this.main = mainController;
        tree.prefWidthProperty().addListener((observable, oldValue, newValue) -> {

        });
    }

    TreeItem<JsonTreeItem> addItem(FieldDTO dto) {

        //take selected node
        TreeItem<JsonTreeItem> selected = tree.getSelectionModel().getSelectedItem();


        //set is isArrayLike?
        if (dto.getType().isArrayLike()) {
            //todo
        }

        //add new node to selected node
        TreeItem<JsonTreeItem> treeItem = new TreeItem<>(new JsonTreeItem(dto));
        selected.getChildren().add(treeItem);

        return null;
    }
}
