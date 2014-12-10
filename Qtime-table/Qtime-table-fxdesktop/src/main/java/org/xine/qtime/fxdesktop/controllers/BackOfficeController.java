package org.xine.qtime.fxdesktop.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import org.xine.fx.guice.FXMLController;

@FXMLController
public class BackOfficeController extends ContentController {

    /** The root. */
    @FXML
    private AnchorPane root;

    /** The nav. */
    @FXML
    private VBox nav;

    /** The component. */
    @FXML
    private VBox component;

    /** The nav items. */
    @FXML
    private VBox navItems;

    public BackOfficeController() {
        super();
        super.setName("BackOffice");
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
        // nothing
        // implements the buttons envents
    }

}
