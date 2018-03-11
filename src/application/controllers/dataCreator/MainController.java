package application.controllers.dataCreator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private DataController dataController;

    @FXML
    private ListController listController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataController.initializeParent(this);
        listController.initializeParent(this);
    }

    public DataController getDataController() {
        return dataController;
    }

    public ListController getListController() {
        return listController;
    }
}
