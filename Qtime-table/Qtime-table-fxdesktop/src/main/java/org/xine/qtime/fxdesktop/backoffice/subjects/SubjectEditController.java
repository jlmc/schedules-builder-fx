/*
 * Copyright (c) 2015 Qxine <https://github.com/jlmc>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */

package org.xine.qtime.fxdesktop.backoffice.subjects;

import org.xine.qtime.entities.Subject;
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
public class SubjectEditController extends StateController<Subject> {

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
        this.backButton.setOnAction(e -> getMachineStatesController().onBack());
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.controllers.ContentController#getRootNode()
     */
    @Override
    public Node getRootNode() {
        return this.root;
    }

    @Override
    public void onActivate() {
        super.onActivate();

        if (getSelected() != null) {
            this.nameField.setText(getSelected().getName());
            this.descriptionField.setText(getSelected().getDescription());
        }
    }

}
