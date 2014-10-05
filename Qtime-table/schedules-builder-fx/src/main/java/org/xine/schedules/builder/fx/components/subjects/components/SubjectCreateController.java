package org.xine.schedules.builder.fx.components.subjects.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.components.ScheduleAbstractContentController;
import org.xine.schedules.builder.fx.components.SubType;
import org.xine.schedules.builder.fx.model.Subject;

/**
 * The Class SubjectCreateController.
 */
@FXMLController
public class SubjectCreateController extends ScheduleAbstractContentController {

    /** The Constant SUBJECTSCONTROLLER. */
    private static final String NAME = "SubjectCreateController";

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

    /** The subject model. */
    private Subject subjectModel;

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

    /**
     * Gets the subject model.
     * @return the subject model
     */
    public Subject getSubjectModel() {
        return this.subjectModel;
    }

    /**
     * Sets the subject model.
     * @param subjectModel
     *            the new subject model
     */
    public void setSubjectModel(final Subject subjectModel) {
        this.subjectModel = subjectModel;
    }

    @FXML
    public void initialize() {
        this.h1.textProperty().set("Subjets");
        this.h2.textProperty().set("create <i>New</i>");

    }

    /**
     * Back click.
     * @param event
     *            the event
     */
    @FXML
    public void backClick(final ActionEvent event) {
        super.getParentComponent().activateController(SubType.LIST);
    }

    /**
     * Save click.
     * @param event
     *            the event
     */
    @FXML
    public void saveClick(final ActionEvent event) {
        super.getParentComponent().activateController(SubType.CREATE);
    }

}
