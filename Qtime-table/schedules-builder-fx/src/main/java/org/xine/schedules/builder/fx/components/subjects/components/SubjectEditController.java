package org.xine.schedules.builder.fx.components.subjects.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.inject.Inject;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.components.ScheduleAbstractContentController;
import org.xine.schedules.builder.fx.components.SubType;
import org.xine.schedules.builder.fx.components.subjects.SubjectDataModel;

/**
 * The Class SubjectEditController.
 */
@FXMLController
public class SubjectEditController extends ScheduleAbstractContentController {

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

    /** The subject name. */
    @FXML
    private TextField subjectName;

    /** The model. */
    @Inject
    private SubjectDataModel model;

    /**
     * Gets the root node.
     * @return the root node
     */
    @Override
    public Node getRootNode() {
        return this.root;
    }

    /**
     * Instantiates a new subject edit controller.
     */
    public SubjectEditController() {
        super();
        setName("SubjectEditController");
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        this.h1.textProperty().set("Subjets");
        this.h2.textProperty().set("edit <i>New</i>");

    }

    /*
     * (non-Javadoc)
     * @see org.xine.schedules.builder.fx.gui.ContentController#onActivate()
     */
    @Override
    public void onActivate() {
        this.subjectName.textProperty().bindBidirectional(this.model.getSelectedObject().nameProperty());

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
        super.getParentComponent().activateController(SubType.LIST);
    }

    /**
     * Delete click.
     * @param event
     *            the event
     */
    @FXML
    public void deleteClick(final ActionEvent event) {
        this.model.getSubject().remove(this.model.getSelectedObject());
        super.getParentComponent().activateController(SubType.LIST);
    }

}
