package org.xine.qtime.fxdesktop.backoffice.subjects;

import org.xine.qtime.fxdesktop.controllers.StateController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * The Class SubjectEditController.
 */
public class SubjectEditController extends StateController {

    /** The resources. */
    @FXML
    private ResourceBundle resources;

    /** The location. */
    @FXML
    private URL location;

    /** The cancel button. */
    @FXML
    private Button cancelButton;

    /** The root. */
    @FXML
    private AnchorPane root;

    /** The back button. */
    @FXML
    private Button backButton;

    /** The name field. */
    @FXML
    private TextField nameField;

    /** The description field. */
    @FXML
    private TextArea descriptionField;

    /** The title. */
    @FXML
    private Label title;

    /** The save button. */
    @FXML
    private Button saveButton;

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        this.backButton.setOnAction(e -> getMachineStatesController().setActiveController(
                getMachineStatesController().getListController()));
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.controllers.ContentController#getRootNode()
     */
    @Override
    public Node getRootNode() {
        return this.root;
    }
}
