package application.core.library.tree;

import application.App;
import application.controllers.structureCreator.MainController;
import application.dto.FieldDTO;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import org.json.JSONObject;


public class JsonTreeItem extends HBox {

    private final FieldDTO dto;
    private final Button   remove;

    private EditableLabel label;
    private Label         type;
    private Label         opts;

    public JsonTreeItem(FieldDTO dto, boolean editable) {
        super();
        this.dto = dto;
        this.prefWidth(Double.MAX_VALUE);
        this.prefHeight(Double.MAX_VALUE);
        label = new EditableLabel(dto.getName());
        type = new Label(dto.getType().getName());
        label.setStyle("-fx-font-weight:bold");

        if (editable) {
            label.baseTextProperty().addListener((observable, oldValue, newValue) -> {
                MainController main = App.getInstance().getController();
                dto.setName(newValue); //todo fix bug with renaming to exist in hierarchy
                main.getTreeController().handler(new Event(TreeItem.TreeModificationEvent.ANY));
            });
        } else {
            label.setEditable(false);
            label.editableProperty().setValue(false);
        }

        this.getChildren().addAll(label, type);

        if (dto.getOpts().size() > 0) {
            opts = new Label(dto.getOpts().toString());
            this.getChildren().add(opts);
        }

        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        remove = new Button("x");
        this.getChildren().addAll(region1, remove);
        if (!editable)
            getRemoveButton().setDisable(true);


    }

    public EditableLabel getLabel() {
        return label;
    }

    public FieldDTO getDTO() {
        return dto;
    }

    public Button getRemoveButton() {
        return remove;
    }

    public JSONObject getSerializeData() {
        JSONObject dto = getDTO().serialize();
        return dto;
    }

    public String getName() {
        return getDTO().getName();
    }

}


