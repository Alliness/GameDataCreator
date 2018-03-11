package application.controllers.dataCreator;

import application.core.utils.JSONUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class DataController implements Initializable {

    @FXML
    private AnchorPane     data;
    private MainController main;

    private JSONObject jsonData;
    private JSONObject currentStructure;
    private String     currentStructureName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void initializeParent(MainController mainController) {
        this.main = mainController;
    }


    public void saveData() {

    }

    /**
     * Assign structure model to data controller
     *
     * @param name            {@link String}
     * @param structureObject {@link JSONObject}
     */
    void setStructureObject(String name, JSONObject structureObject) {
        if (currentStructure != null || currentStructureName != null) {
            // todo notify, structure is already defined
        }
        currentStructure = structureObject;
        currentStructureName = name;

        //set data name
        //add button for as-array view
        //iterate json
        //check each for $params
        // assign value to params
        //


        System.out.println(JSONUtils.pretty(currentStructure));
    }
}
