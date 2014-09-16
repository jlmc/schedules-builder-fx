package org.xine.schedules.builder.fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.gui.ContentController;

@FXMLController
public class SubjectController extends ContentController {

    @FXML
    private AnchorPane root;

    public SubjectController() {
        super();
        setName("subjets");
    }

    @Override
    public Node getRootNode() {
        return this.root;
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        // TODO
    }

}
