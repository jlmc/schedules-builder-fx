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

@FXMLController
public class SubjectCreateController extends BackofficeContentController<Subject> {

    @FXML
    private AnchorPane root;

    @FXML
    private Button cancelButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private Label title;

    @FXML
    private Button saveButton;

    public SubjectCreateController() {
        super();
        // don't need to do the next define
        setName("SubjectCreateController");
    }

    @Override
    public Node getRootNode() {
        return this.root;
    }

    @FXML
    public void initialize() {
        this.backButton.setOnAction(e -> getContentDecorated().changeStatus(Status.LIST));
        this.saveButton.setOnAction(e -> save());
        this.cancelButton.setOnAction(e -> cancel());

    }

    private void save() {
        getContentDecorated().changeStatus(Status.LIST);
    }

    private void cancel() {
        getContentDecorated().changeStatus(Status.LIST);
    }
}
