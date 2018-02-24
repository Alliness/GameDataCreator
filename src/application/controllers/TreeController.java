package application.controllers;

import application.core.library.field.types.FieldTypeEnum;
import application.core.library.tree.JsonTreeItem;
import application.core.utils.JSONUtils;
import application.dto.FieldDTO;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.json.JSONArray;
import org.json.JSONObject;

public class TreeController {

    private MainController main;

    @FXML
    public  TreeView<JsonTreeItem> tree;
    private JsonTreeItem           rootItem;
    private JSONObject             skeleton;

    @FXML
    private void initialize() {
        rootItem = new JsonTreeItem(new FieldDTO(null, "name", FieldTypeEnum.OBJECT));
        rootItem.getRemoveButton().setDisable(true);
        TreeItem<JsonTreeItem> root = new TreeItem<>(rootItem);
        root.setExpanded(true);
        root.addEventHandler(TreeItem.TreeModificationEvent.ANY, this::handler);
        tree.setRoot(root);

    }

    /**
     * main controller setter
     *
     * @param mainController {@link MainController}
     */
    void initializeParent(MainController mainController) {
        this.main = mainController;
        main.fileName(rootItem.getDTO().getName());
    }

    /**
     * tree modification handler for refresh render of current json tree.
     */
    public <E extends Event> void handler(E event) {
        skeleton = new JSONObject();
        renderTree(skeleton, tree.getRoot());
        System.out.println(skeleton);
//        System.out.println(JSONUtils.pretty(skeleton));
    }

    /**
     * recursive jsontree appender
     */
    private void renderTree(JSONObject obj, TreeItem<JsonTreeItem> tree) {

        for (TreeItem<JsonTreeItem> item : tree.getChildren()) {
            JsonTreeItem jsonItem = item.getValue();
            obj.put(jsonItem.getName(), new JSONObject().put("params", jsonItem.getSerializeData()));
            if (jsonItem.getDTO().getType().isArrayLike()) {
                renderTree(obj.getJSONObject(jsonItem.getName()), item);
            }
        }
    }

    /**
     * tree Item creator/appender
     *
     * @param dto {@link FieldDTO}
     */
    void addItem(FieldDTO dto) {
        //take selected node
        TreeItem<JsonTreeItem> selected = tree.getSelectionModel().getSelectedItem();

        //selected item not null
        if (selected == null) {
            main.printMessage("Select parent node first!");
            return;
        }
        //name not exist in hierarchy
        if (itemNameExist(selected, dto.getName())) {
            main.printMessage("Item already exist in same hierarchy");
            return;
        }
        //if selected item is array-like
        if (!selected.getValue().getDTO().getType().isArrayLike()) {
            main.printMessage("Unable add child to non-array-like parent");
            return;
        }
        //create new item
        TreeItem<JsonTreeItem> treeItem = new TreeItem<>(new JsonTreeItem(dto));
        //set action on remove
        treeItem.getValue().getRemoveButton().setOnAction(event -> removeItem(tree.getRoot(), treeItem));
        //append to parent
        selected.getChildren().add(treeItem);

    }

    /**
     * check for name in current hierarchy
     */
    private boolean itemNameExist(TreeItem<JsonTreeItem> selected, String name) {
        for (TreeItem<JsonTreeItem> jsonTreeItemTreeItem : selected.getChildren()) {
            if (jsonTreeItemTreeItem.getValue().getDTO().getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * remove item recursive from tree
     */
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

    public JSONObject getSkeleton() {
        return skeleton;
    }

    public String getRootName() {
        return rootItem.getName();
    }
}
