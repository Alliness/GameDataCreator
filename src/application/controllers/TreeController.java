package application.controllers;

import application.core.library.field.types.FieldTypeEnum;
import application.core.library.tree.JsonTreeItem;
import application.core.utils.JSONUtils;
import application.dto.FieldDTO;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import netscape.javascript.JSUtil;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TreeController {

    private MainController main;

    @FXML
    public  TreeView<JsonTreeItem> tree;
    private TreeItem<JsonTreeItem> rootItem;
    private JSONObject             skeleton;

    private static final List<String> allowedEvents = new ArrayList<String>() {{ //tree update events
        add("ChildrenModificationEvent");
        add("TreeNotificationEvent");
    }};

    @FXML
    private void initialize() {}

    /**
     * main controller setter
     *
     * @param mainController {@link MainController}
     */
    void initializeParent(MainController mainController) {
        this.main = mainController;
    }

    /**
     * tree modification handler for refresh render of current json tree.
     */
    public <E extends Event> void handler(E event) {
        if (allowedEvents.contains(event.getEventType().getName())) {
            skeleton = new JSONObject();
            renderTree(skeleton, tree.getRoot());
            main.setViewText(JSONUtils.pretty(skeleton));
            System.out.println(JSONUtils.pretty(skeleton));
        }

    }

    /**
     * recursive jsonTree creator
     */
    private void renderTree(JSONObject obj, TreeItem<JsonTreeItem> tree) {

        for (TreeItem<JsonTreeItem> item : tree.getChildren()) {
            JsonTreeItem jsonItem = item.getValue();
            obj.put(jsonItem.getName(), new JSONObject().put("$params", jsonItem.getSerializeData()));
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

        if (rootItem == null) {
            dto.setType(FieldTypeEnum.OBJECT);
            JsonTreeItem jsonItem = new JsonTreeItem(dto);
            jsonItem.getRemoveButton().setDisable(true);
            rootItem = new TreeItem<>(jsonItem);
            rootItem.setExpanded(true);
            rootItem.addEventHandler(TreeItem.TreeModificationEvent.ANY, this::handler);
            tree.setRoot(rootItem);
            return;
        }

        //take selected node
        TreeItem<JsonTreeItem> selected = tree.getSelectionModel().getSelectedItem();

        //selected item not null
        if (selected == null) {
            main.printMessage("Select parent node first!");
            return;
        }
        //name not exist in hierarchy
        if (itemNameExist(selected, dto.getName())) {
            main.printMessage("Item name already exist in same hierarchy");
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

        treeItem.setExpanded(true);
        //append to parent
        selected.getChildren().add(treeItem);
        main.setViewText(JSONUtils.pretty(skeleton));

    }

    /**
     * check for name in current hierarchy
     */
    public boolean itemNameExist(TreeItem<JsonTreeItem> selected, String name) {
        for (TreeItem<JsonTreeItem> jsonTreeItemTreeItem : selected.getChildren()) {
            if (jsonTreeItemTreeItem.getValue().getName().equals(name)) {
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
        return rootItem.getValue().getName();
    }
}
