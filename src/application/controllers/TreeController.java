package application.controllers;

import application.core.library.field.types.FieldTypeEnum;
import application.core.library.tree.JsonTreeItem;
import application.dto.FieldDTO;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class TreeController {

    private MainController main;

    @FXML
    public TreeView<JsonTreeItem> tree;

    @FXML
    private void initialize() {
        JsonTreeItem           rootItem = new JsonTreeItem(new FieldDTO(null, "name", FieldTypeEnum.OBJECT));
        rootItem.getRemoveButton().setDisable(true);
        TreeItem<JsonTreeItem> root     = new TreeItem<>(rootItem);
        root.setExpanded(true);
        tree.setRoot(root);
    }

    void initializeParent(MainController mainController) {
        this.main = mainController;
    }

    void addItem(FieldDTO dto) {
        //take selected node
        TreeItem<JsonTreeItem> selected = tree.getSelectionModel().getSelectedItem();
        if (selected == null) {
            //todo create notice
            return;
        }
        if (selected.getValue().getDTO().getType().isArrayLike()) {
            TreeItem<JsonTreeItem> treeItem = new TreeItem<>(new JsonTreeItem(dto));
            treeItem.getValue().getRemoveButton().setOnAction(event -> removeItem(tree.getRoot(), treeItem));
            selected.getChildren().add(treeItem);
        } else {
            System.out.println("unable add child to non-arrayLike object");
            //todo create notification
        }


        //set is isArrayLike?
        if (dto.getType().isArrayLike()) {
            //todo
        }

        //add new node to selected node
    }

    private void removeItem(TreeItem<JsonTreeItem> level, TreeItem<JsonTreeItem> target) {
        for (TreeItem<JsonTreeItem> child : level.getChildren()) {
            if (child.equals(target)) {
                level.getChildren().remove(target);
                return;
            } else {
                removeItem(child, target);
            }
        }
    }
}
