/*
 * 
 */
package org.xine.schedules.builder.fx.backoffice.subject;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.backoffice.BackofficeContentController;
import org.xine.schedules.builder.fx.backoffice.Status;
import org.xine.schedules.builder.fx.model.Subject;

/**
 * The Class SubjectCreateController.
 */
@FXMLController
public class SubjectCreateController extends BackofficeContentController<Subject> {

    /** The root. */
    @FXML
    private AnchorPane root;

    /** The cancel button. */
    @FXML
    private Button cancelButton;

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
     * Instantiates a new subject create controller.
     */
    public SubjectCreateController() {
        super();
        // don't need to do the next define
        setName("SubjectCreateController");
    }

    /* (non-Javadoc)
     * @see org.xine.schedules.builder.fx.gui.ContentController#getRootNode()
     */
    @Override
    public Node getRootNode() {
        return this.root;
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        this.backButton.setOnAction(e -> getContentDecorated().changeStatus(Status.LIST));
        this.saveButton.setOnAction(e -> save());
        this.cancelButton.setOnAction(e -> cancel());

    }

    /**
     * Save.
     */
    private void save() {
        getContentDecorated().changeStatus(Status.LIST);
    }

    /**
     * Cancel.
     */
    private void cancel() {
        getContentDecorated().changeStatus(Status.LIST);
    }
}
