package org.xine.schedules.builder.fx.components.subjects.components;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.components.ScheduleAbstractContentController;

/**
 * The Class SubjectCreateController.
 */
@FXMLController
public class SubjectCreateController extends ScheduleAbstractContentController {

    /** The Constant NAME. */
    public static final String NAME = "subjectCreateController";

    /** The root. */
    @FXML
    private AnchorPane root;

    /**
     * Instantiates a new subject create controller.
     */
    public SubjectCreateController() {
        super();
        setName(NAME);
    }

    /**
     * Gets the root node.
     * @return the root node
     * @see org.xine.schedules.builder.fx.gui.ContentController#getRootNode()
     */
    @Override
    public Node getRootNode() {
        return this.root;
    }

}
