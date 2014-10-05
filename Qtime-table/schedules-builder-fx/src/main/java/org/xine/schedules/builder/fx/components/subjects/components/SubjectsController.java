/**
 *
 */
package org.xine.schedules.builder.fx.components.subjects.components;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.components.ScheduleAbstractContentController;
import org.xine.schedules.builder.fx.components.ScheduleModel;
import org.xine.schedules.builder.fx.components.SubType;
import org.xine.schedules.builder.fx.components.utils.ActionsTableCell;
import org.xine.schedules.builder.fx.components.utils.DummySource;
import org.xine.schedules.builder.fx.model.Subject;

/**
 * The Class SubjectsController.
 * @author JLC
 */

@FXMLController
public class SubjectsController extends ScheduleAbstractContentController {

    /** The Constant SUBJECTSCONTROLLER. */
    private static final String SUBJECTSCONTROLLER = "subjectsController";

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

    /** The table. */
    @FXML
    private TableView<Subject> table;

    /** The idc. */
    @FXML
    private TableColumn<Subject, Number> idc;

    /** The subjectc. */
    @FXML
    private TableColumn<Subject, String> subjectc;

    /** The actionsc. */
    @FXML
    private TableColumn<Subject, Subject> actionsc;

    /** The model. */
    private final ScheduleModel<Subject> model;

    /**
     * Instantiates a new subjects controller.
     */
    public SubjectsController() {
        super();
        setName(SUBJECTSCONTROLLER);
        this.model = new ScheduleModel<>();

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
     * @param <T>
     *            the generic type
     */
    @FXML
    public <T> void initialize() {
        this.h1.textProperty().set("Subjets List");
        this.h2.textProperty().set("");

        DummySource.builderSubjects(this.model);

        this.table.setItems(this.model.getSubjectsData());

        this.idc.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getIdProperty());
        this.subjectc.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().nameProperty());
        this.actionsc.setCellValueFactory(cellDataFeatures -> {
            return new SimpleObjectProperty<>(cellDataFeatures.getValue());
        });

        // / action collumn
        this.actionsc.setCellFactory(tableColumn -> {
            return actionsCellBuilder();
        });

        this.actionsc.setSortable(false);

        // define width
        this.idc.prefWidthProperty().bind(this.table.widthProperty().multiply(0.040));
        this.subjectc.prefWidthProperty().bind(this.table.widthProperty().multiply(0.60));
        this.actionsc.prefWidthProperty().bind(this.table.widthProperty().multiply(0.35));
    }

    /**
     * Actions cell builder.
     * @return the table cell
     */
    protected TableCell<Subject, Subject> actionsCellBuilder() {
        final ActionsTableCell<Subject, Subject> actionsTableCell = new ActionsTableCell<>();

        // actionsTableCell.addEventHandler(ActionsTableCellViewEvent.VIEW_EVENT, new EventHandler<Event>() {
        //
        // @Override
        // public void handle(final Event e) {
        //
        // if (((ActionsTableCell) e.getTarget()) != null) {
        // final Subject subject = (Subject) ((ActionsTableCell) e.getTarget()).getData();
        //
        // System.out.println("o button View clicado: " + subject.getId() + " name:" + subject.getName());
        // }
        //
        // }
        // });

        // actionsTableCell.setOnViewAction(new EventHandler<ActionEvent>() {
        //
        // @Override
        // public void handle(final ActionEvent event) {
        // final Subject s = actionsTableCell.getData();
        //
        // System.out.println("o button View clicadoXXXX: " + s.getId() + " name:" + s.getName());
        // }
        // });

        actionsTableCell.setOnEditAction(e -> {
            final Subject s = actionsTableCell.getData();
            setStatus(SubType.EDIT, s);
        });

        actionsTableCell.setOnViewAction(e -> {
            final Subject s = actionsTableCell.getData();
            setStatus(SubType.VIEW, s);
        });

        actionsTableCell.setOnDeleteAction(e -> {
            final Subject s = actionsTableCell.getData();
            if (s != null) {
                this.model.getSubjectsData().remove(s);
            }
        });

        // actionsTableCell.addEventHandler(ActionsTableCellEditEvent.EDIT_EVENT, e -> {
        // System.out.println("edit clicked");
        // });

        // actionsTableCell.addEventHandler(ActionsTableCellDeleteEvent.DELETE_EVENT, e -> {
        // System.out.println("deleted clicked");
        // });

        return actionsTableCell;
    }

    /**
     * Sets the status.
     * @param subType
     *            the sub type
     * @param subject
     *            the subject
     */
    private void setStatus(final SubType subType, final Subject subject) {
        this.model.setSelectedObject(subject);
        setStatus(subType);
    }

    /**
     * Sets the status.
     * @param subType
     *            the new status
     */
    private void setStatus(final SubType subType) {
        if (subType != null) {
            super.getParentComponent().activateController(subType);
        }
    }

    /**
     * Creates the click.
     * @param event
     *            the event
     */
    @FXML
    protected void createClick(final ActionEvent event) {
        System.out.println("create click action");
        super.getParentComponent().activateController(SubType.CREATE);
    }

}
