package org.xine.schedules.builder.fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.inject.Inject;

import org.xine.fx.guice.FXMLController;
import org.xine.fx.guice.GuiceFXMLLoader;
import org.xine.schedules.builder.fx.backoffice.subject.SubjectAnchorPane;
import org.xine.schedules.builder.fx.gui.ContentController;

/**
 * The Class BackOfficeController.
 */
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

    /** The fxml loader. */
    @Inject
    private GuiceFXMLLoader fxmlLoader;

    /** The subjects. */
    @FXML
    private SubjectAnchorPane subjectAnchorPane;

    /**
     * Instantiates a new back office controller.
     */
    public BackOfficeController() {
        super();
        super.setName("BackOffice");
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        // nothing
        // implements the buttons envents
    }

    /*
     * (non-Javadoc)
     * @see org.xine.schedules.builder.fx.gui.ContentController#getRootNode()
     */
    @Override
    public Node getRootNode() {
        return this.root;
    }

}
