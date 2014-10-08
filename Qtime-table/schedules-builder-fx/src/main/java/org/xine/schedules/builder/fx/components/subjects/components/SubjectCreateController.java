package org.xine.schedules.builder.fx.components.subjects.components;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.inject.Inject;

import jidefx.scene.control.validation.ValidationEvent;
import jidefx.scene.control.validation.ValidationMode;
import jidefx.scene.control.validation.ValidationObject;
import jidefx.scene.control.validation.ValidationUtils;
import jidefx.scene.control.validation.Validator;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.components.ScheduleAbstractContentController;
import org.xine.schedules.builder.fx.components.SubType;
import org.xine.schedules.builder.fx.components.subjects.SubjectDataModel;
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

    /** The subject name. */
    @FXML
    private TextField subjectName;

    /** The model. */
    @Inject
    private SubjectDataModel model;

    /** The random. */
    @Inject
    private Random random;

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
     * Initialize.
     */
    @FXML
    public void initialize() {
        this.h1.textProperty().set("Subjets");
        this.h2.textProperty().set("create <i>New</i>");

        // VALIDATION demo test
        ValidationUtils.install(this.subjectName, new Validator() {

            @Override
            public ValidationEvent call(final ValidationObject param) {
                System.out.println("i'm in validation thing");
                return new ValidationEvent(ValidationEvent.VALIDATION_ERROR, 15, ValidationEvent.FailBehavior.REVERT);

            }
        }, ValidationMode.ON_FOCUS_LOST);

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

        if (this.subjectName.textProperty().get() != null && !this.subjectName.textProperty().get().trim().isEmpty()) {
            final Subject subject = new Subject();
            subject.setId(this.random.nextInt());
            subject.setName(this.subjectName.getText());

            this.model.getSubject().add(subject);
        }

        super.getParentComponent().activateController(SubType.LIST);

    }

}
