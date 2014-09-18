package org.xine.schedules.builder.fx.components.subjects.components;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import org.xine.schedules.builder.fx.components.ScheduleAbstractContentController;

/**
 * The Class SubjectDetailsController.
 */
public class SubjectDetailsController extends ScheduleAbstractContentController {

    /** The Constant NAME. */
    public static final String NAME = "subjectDetailsController";

    /** The root. */
    @FXML
    private AnchorPane root;

    /**
     * Instantiates a new subject details controller.
     */
    public SubjectDetailsController() {
        super();
        setName(NAME);
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
