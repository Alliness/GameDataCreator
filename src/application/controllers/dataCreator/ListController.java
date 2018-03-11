package application.controllers.dataCreator;

import application.core.utils.Dir;
import application.core.utils.FReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ListController implements Initializable {

    @FXML
    private ListView<String> listView;
    @FXML
    private AnchorPane       list;

    private HashMap<String, JSONObject> files;
    private MainController              main;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        files = FReader.readAll(Dir.LIBS);

        ArrayList<String> items = new ArrayList<>();
        items.addAll(files.keySet());
        ObservableList<String> obs = FXCollections.observableArrayList(items);
        listView.setItems(obs);
        listView.onMouseClickedProperty().set(this::librarySelected);

    }

    void initializeParent(MainController mainController) {
        this.main = mainController;
    }

    /**
     * event handler for selected list item
     * @param mouseEvent {@link MouseEvent}
     */
    private void librarySelected(MouseEvent mouseEvent) {

        String selectedItem = listView.getSelectionModel().getSelectedItem();

        main.getDataController().setStructureObject(selectedItem, files.get(selectedItem));
        //todo
        // check, is other data already selected (use warning popup)
        //get selected lib by key, and send json model to data controller

    }
}
