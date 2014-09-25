package org.xine.schedules.builder.fx.components.subjects.components;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.components.ScheduleAbstractContentController;

/**
 * The Class SubjectListController.
 */
@FXMLController
public class SubjectListController extends ScheduleAbstractContentController {

    /** The Constant NAME. */
    public static final String NAME = "subjectListController";

    /** The root. */
    @FXML
    private AnchorPane root;

    @FXML
    private HBox footer;

    /**
     * Instantiates a new subject list controller.
     */
    public SubjectListController() {
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

    /**
     * Selection action.
     */
    @SuppressWarnings("static-method")
    @FXML
    public void selectionAction() {
        System.out.println("selection action");
    }

}
