package org.xine.schedules.builder.fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.gui.ContentController;

// TODO: Auto-generated Javadoc
/**
 * The Class RoomsController.
 */
@FXMLController
public class RoomsController extends ContentController {

    /** The root. */
    @FXML
    private AnchorPane root;

    /**
     * Instantiates a new rooms controller.
     */
    public RoomsController() {
        super();
        setName("rooms");
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
