package application.core.dataGenerator;

import application.dto.ParamDTO;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public abstract class AbstractDataFieldItem extends HBox {
    private Label    label;// view: name of item
    private Label    type; // view: type of item
    //todo add icon
    private ParamDTO paramDTO; // inner: data item

}
