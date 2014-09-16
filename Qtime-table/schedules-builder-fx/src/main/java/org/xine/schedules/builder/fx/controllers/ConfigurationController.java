package org.xine.schedules.builder.fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.gui.ContentController;

// TODO: Auto-generated Javadoc
//@FXMLComponent

/**
 * The Class ConfigurationController.
 */
@FXMLController
public class ConfigurationController extends ContentController {

    /** The input field. */
    @FXML
    private TextArea inputField;  

    /** The chat area. */
    @FXML
    private TextArea chatArea;

    /** The root. */
    @FXML
    private AnchorPane root;

    /**
     * Instantiates a new configuration controller.
     */
    public ConfigurationController() {
        super();
        setName("Configuration");
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
        // TODO
    }

}
