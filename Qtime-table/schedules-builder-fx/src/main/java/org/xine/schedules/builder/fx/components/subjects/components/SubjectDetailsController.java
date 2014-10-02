package org.xine.schedules.builder.fx.components.subjects.components;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.xine.schedules.builder.fx.components.ScheduleAbstractContentController;

/**
 * The Class SubjectDetailsController.
 */
public class SubjectDetailsController extends ScheduleAbstractContentController {

    /** The Constant SUBJECTSCONTROLLER. */
    private static final String NAME = "SubjectDetailsController";

    /** The internal. */
    @FXML
    private VBox internal;

    /** The root. */
    @FXML
    private AnchorPane root;

    /** The header. */
    @FXML
    private HBox header;

    /** The h1. */
    @FXML
    private Label h1;

    /** The main. */
    @FXML
    private VBox main;

    /** The h2. */
    @FXML
    private Label h2;

    /** The foot. */
    @FXML
    private HBox foot;

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
