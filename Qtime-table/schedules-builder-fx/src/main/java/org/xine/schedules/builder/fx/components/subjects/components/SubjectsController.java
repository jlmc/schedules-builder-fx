/**
 *
 */
package org.xine.schedules.builder.fx.components.subjects.components;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.components.ScheduleAbstractContentController;
import org.xine.schedules.builder.fx.components.utils.ActionsTableCell;
import org.xine.schedules.builder.fx.components.utils.ActionsTableCellDeleteEvent;
import org.xine.schedules.builder.fx.components.utils.ActionsTableCellEditEvent;
import org.xine.schedules.builder.fx.components.utils.ActionsTableCellViewEvent;
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
    private final SubjectsModel model;

    /**
     * Instantiates a new subjects controller.
     */
    public SubjectsController() {
        super();
        setName(SUBJECTSCONTROLLER);
        this.model = new SubjectsModel();

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

        builderSubjects(this.model);

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
        this.idc.prefWidthProperty().bind(this.table.widthProperty().multiply(0.05));
        this.subjectc.prefWidthProperty().bind(this.table.widthProperty().multiply(0.60));
        this.actionsc.prefWidthProperty().bind(this.table.widthProperty().multiply(0.34));
    }

    /**
     * Actions cell builder.
     * @return the table cell
     */
    @SuppressWarnings({"rawtypes", "static-method" })
    protected TableCell<Subject, Subject> actionsCellBuilder() {
        final ActionsTableCell<Subject, Subject> actionsTableCell = new ActionsTableCell<>();

        actionsTableCell.addEventHandler(ActionsTableCellViewEvent.VIEW_EVENT, new EventHandler<Event>() {

            @Override
            public void handle(final Event e) {

                // final TableCell cell = (TableCell) e.getSource();

                final ActionsTableCell c = (ActionsTableCell) e.getTarget();
                if (c != null) {
                    // final Subject subject = (Subject) cell.getUserData();
                    final Subject subject = (Subject) c.getData();

                    System.out.println("o button View clicado: " + subject.getId() + " name:" + subject.getName());
                }

            }
        });

        actionsTableCell.addEventHandler(ActionsTableCellEditEvent.EDIT_EVENT, e -> {
            System.out.println("edit clicked");
        });

        actionsTableCell.addEventHandler(ActionsTableCellDeleteEvent.DELETE_EVENT, e -> {
            System.out.println("deleted clicked");
        });

        return actionsTableCell;
    }

    /**
     * Builder subjects.
     * @param model
     *            the model
     */
    private static void builderSubjects(final SubjectsModel model) {
        final List<Subject> ss = new ArrayList<>();

        Subject s = new Subject();
        s.setId(1);
        s.setName("Ana");
        ss.add(s);

        s = new Subject();
        s.setId(2);
        s.setName("Bruno");
        ss.add(s);

        s = new Subject();
        s.setId(3);
        s.setName("Cesar");
        ss.add(s);

        model.setSubjectsData(ss);
    }

    /**
     * The Class ButtonCell.
     */
    public class ButtonCell extends TableCell<Subject, Boolean> {

        /** The cell button. */
        final Button cellButton = new Button("X");

        /**
         * Display button if the row is not empty.
         * @param item
         *            the item
         * @param empty
         *            the empty
         */
        @Override
        protected void updateItem(final Boolean item, final boolean empty) {
            super.updateItem(item, empty);

            if (!empty) {
                setGraphic(this.cellButton);
            }
        }

        /**
         * Instantiates a new button cell.
         */
        public ButtonCell() {
            super();
            this.cellButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(final ActionEvent event) {
                    System.out.println("X-> cliced");
                    // do something when button clicked
                    // ...
                }
            });
        }
    }

}