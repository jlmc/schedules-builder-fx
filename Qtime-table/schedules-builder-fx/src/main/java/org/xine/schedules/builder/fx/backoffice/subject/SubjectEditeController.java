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
 * The Class SubjectEditeController.
 */
@FXMLController
public class SubjectEditeController extends BackofficeContentController<Subject> {

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
     * Instantiates a new subject edite controller.
     */
    public SubjectEditeController() {
        super();
        // don't need to do the next define
        setName("SubjectEditeController");
    }

    /**
     * Gets the root node.
     * @return the root node
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

        this.backButton.setOnAction(e -> back());
        this.cancelButton.setOnAction(e -> cancel());
        this.saveButton.setOnAction(e -> save());

        // this.nameField.textProperty().bind(getSelected().nameProperty());

        // this.nameField.textProperty(Bindings.when(getSelectedProperty().isNotNull()).then("XX").otherwise(getSelected().nameProperty()));

        // this.nameField.textProperty().bind(Bindings.createStringBinding(() -> getSelectedProperty().isNull().get() ? "null" : getSelectedProperty().get().getName(), getSelectedProperty().get().nameProperty()));
    }

    private void save() {
        // TODO:: missing implementation
        getSelected().setName(this.nameField.getText().trim());

        getContentDecorated().changeStatus(Status.LIST);
    }

    @Override
    public void onActivate() {
        System.out.println("EDIT-" + getSelected().getId());
        this.nameField.setText(getSelected().getName());
    }

    /**
     * Cancel.
     */
    private void cancel() {
        getContentDecorated().changeStatus(Status.LIST);
    }

    /**
     * Back.
     */
    private void back() {
        // TODO:: clear event
        getContentDecorated().changeStatus(Status.LIST);
    }

}
