/*
 * Copyright (c) 2015 Qxine <https://github.com/jlmc>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */

package org.xine.qtime.fxdesktop.backoffice.subjects;

import org.xine.qtime.client.connector.SubjectConnector;
import org.xine.qtime.entities.Subject;
import org.xine.qtime.fxdesktop.controllers.StateController;
import org.xine.qtime.fxdesktop.services.SimpleService;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.inject.Inject;

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

    /** The busy. */
    private final BooleanProperty busy = new SimpleBooleanProperty(false);

    /* ************************************************
     * SERVICE
     * ************************************************
     */
    /** The edit service. */
    @Inject
    private SimpleService<Subject> editService;

    /** The connector. */
    @Inject
    private SubjectConnector connector;

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        this.backButton.setOnAction(e -> getMachineStatesController().onBack());
        this.saveButton.setOnAction(e -> save());
        this.cancelButton.setOnAction(e -> fill());
        this.busy.bind(this.editService.runningProperty());
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.controllers.ContentController#getRootNode()
     */
    /**
     * Gets the root node.
     * @return the root node
     */
    @Override
    public Node getRootNode() {
        return this.root;
    }

    /**
     * On activate.
     */
    @Override
    public void onActivate() {
        super.onActivate();

        fill();
    }

    /**
     * Fill.
     */
    private void fill() {
        if (getSelected() != null) {
            this.nameField.setText(getSelected().getName());
            this.descriptionField.setText(getSelected().getDescription());
        }
    }

    /**
     * Save.
     */
    private void save() {
        if (getSelected() != null) {

            final Subject tosave = new Subject(getSelected().getId(), this.nameField.getText(),
                    this.nameField.getText(), this.descriptionField.getText());

            this.editService.setOnSucceeded(e -> {
                selectedProperty().get().setName(this.editService.getValue().getName());
                selectedProperty().get().setDescription(
                        this.editService.getValue().getDescription());
                selectedProperty().get().setAcronym(this.editService.getValue().getAcronym());
                selectedProperty().get().setId(this.editService.getValue().getId());

                getMachineStatesController().onEdited(this.editService.getValue());
            });

            this.editService.execute(() -> this.connector.update(tosave),
                    getApplicationController().getExecuterService());
        }
    }

}
