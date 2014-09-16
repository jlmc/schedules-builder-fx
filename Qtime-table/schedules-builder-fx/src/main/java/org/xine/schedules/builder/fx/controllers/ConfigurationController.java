package org.xine.schedules.builder.fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.gui.ContentController;

//@FXMLComponent

@FXMLController
public class ConfigurationController extends ContentController {

    @FXML
    private TextArea inputField;

    @FXML
    private TextArea chatArea;

    @FXML
    private AnchorPane root;

    public ConfigurationController() {
        super();
        setName("Configuration");
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
