package org.xine.schedules.builder.fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.components.subjects.SubjectsPane;
import org.xine.schedules.builder.fx.gui.ContentController;

/**
 * The Class SubjectController.
 */
@FXMLController
public class SubjectController extends ContentController {

    /** The root. */
    @FXML
    private AnchorPane root;
    
    @FXML
    private SubjectsPane subjectsPane;

    /**
     * Instantiates a new subject controller.
     */
    public SubjectController() {
        super();
        setName("subjets");
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
    }

}
