package org.xine.schedules.builder.fx.controllers.office;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.gui.ContentController;

/**
 * The Class SubjectController.
 */
@FXMLController
public class SubjectController extends ContentController {

    /** The root. */
    @FXML
    private AnchorPane root;

    /**
     * Instantiates a new subject controller.
     */
    public SubjectController() {
        super();
        setName("Subjects");
    }

    /*
     * (non-Javadoc)
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
        // TODO::
    }

}
