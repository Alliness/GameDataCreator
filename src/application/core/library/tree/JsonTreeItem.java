package application.core.library.tree;

import application.dto.FieldDTO;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class JsonTreeItem extends HBox {

    private final FieldDTO dto;
    private final Button remove;

    private EditableLabel label;
    private Label type;
    private Label opts;


    public JsonTreeItem(FieldDTO dto) {
        super();
        this.dto = dto;
        this.prefWidth(Double.MAX_VALUE);
        this.prefHeight(Double.MAX_VALUE);
        label = new EditableLabel(dto.getName());
        type = new Label(dto.getType().getName());
        label.setStyle("-fx-font-weight:bold");
        this.getChildren().addAll(label, type);

        if (dto.getOpts().size() > 0) {
            opts = new Label(dto.getOpts().toString());
            this.getChildren().add(opts);
        }
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        remove = new Button("x");

        this.getChildren().addAll(region1, remove);

    }

    public FieldDTO getDTO() {
        return dto;
    }

    public Button getRemoveButton() {
        return remove;
    }
}


