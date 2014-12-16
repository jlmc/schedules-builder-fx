package org.xine.qtime.fxdesktop.backoffice.subjects;

import org.xine.qtime.fxdesktop.controllers.StateController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * The Class SubjectListController.
 */
public class SubjectListController extends StateController {

    /**
     * The resources.
     * ResourceBundle that was given to the FXMLLoader.
     */
    @FXML
    private ResourceBundle resources;

    /**
     * The location.
     * URL location of the FXML file that was given to the FXMLLoader.
     */
    @FXML
    private URL location;

    /** The name column. */
    @FXML
    private TableColumn<?, ?> nameColumn;

    /** The actions column. */
    @FXML
    private TableColumn<?, ?> actionsColumn;

    /** The root. */
    @FXML
    private AnchorPane root;

    /** The create button. */
    @FXML
    private Button createButton;

    /** The title. */
    @FXML
    private Label title;

    /** The table. */
    @FXML
    private TableView<?> table;

    /** The description column. */
    @FXML
    private TableColumn<?, ?> descriptionColumn;

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.controllers.ContentController#getRootNode()
     */
    @Override
    public Node getRootNode() {
        return this.root;
    }

    /*
     * (non-Javadoc)
     * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
     */

    @FXML
    public void initialize() {

        this.createButton.setOnAction(e -> getMachineStatesController().setActiveController(
                getMachineStatesController().getCreateController()));
    }

}
