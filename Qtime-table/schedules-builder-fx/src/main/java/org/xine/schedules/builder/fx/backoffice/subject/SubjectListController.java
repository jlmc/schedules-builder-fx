package org.xine.schedules.builder.fx.backoffice.subject;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.backoffice.BackofficeContentController;
import org.xine.schedules.builder.fx.backoffice.Editor;
import org.xine.schedules.builder.fx.backoffice.Status;
import org.xine.schedules.builder.fx.components.utils.ActionsTableCell;
import org.xine.schedules.builder.fx.model.Subject;

/**
 * The Class SubjectListController.
 */
@FXMLController
public class SubjectListController extends BackofficeContentController implements Editor {

    /*
     * **************************************************************************
     * *
     * Instance Variables *
     * *
     * ************************************************************************
     */

    /** The root. */
    @FXML
    private AnchorPane root;

    /** The name column. */
    @FXML
    private TableColumn<Subject, String> nameColumn;

    /** The actions column. */
    @FXML
    private TableColumn<Subject, Subject> actionsColumn;

    /** The create button. */
    @FXML
    private Button createButton;

    /** The title. */
    @FXML
    private Label title;

    /** The table. */
    @FXML
    private TableView<Subject> table;

    /** The description column. */
    @FXML
    private TableColumn<Subject, String> descriptionColumn;

    /*
     * the model properties
     */
    final ObservableList<Subject> subjects = FXCollections.observableArrayList();

    private Subject selected;

    /*
     * *************************************************************************
     * *
     * Constructors *
     * *
     * ************************************************************************.
     */

    /**
     * Instantiates a new subject list controller.
     */
    public SubjectListController() {
        super();
        setName("SubjectListController");

        // final List<Integer> ints;
        // ints.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0D);
    }

    @FXML
    public void initialize() {
        this.createButton.setOnAction(e -> getContentDecorated().changeStatus(Status.CREATE));

        for (int i = 0; i < 100; i++) {
            this.subjects.add(new Subject(i + 1, String.format("Subject %d", i + 1)));
        }

        this.nameColumn.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().nameProperty());
        this.actionsColumn.setCellValueFactory(cellDataFeatures -> new SimpleObjectProperty<>(cellDataFeatures.getValue()));
        this.actionsColumn.setCellFactory(tableColumn -> {
            final ActionsTableCell<Subject, Subject> cell = new ActionsTableCell<>();

            cell.setOnDeleteAction(e -> {
                final Subject s = cell.getData();
                if (s != null) {
                    // TODO::missing delete implemetation

                    System.out.println("Delete: " + s.getName());
                }
            });

            cell.setOnEditAction(e -> {
                final Subject selected = cell.getData();
                // TODO :: missing implementation
                System.out.println("Edit Change " + selected.getName());
                getContentDecorated().changeStatus(Status.EDIT);
            });

            return cell;
        });

        this.table.setItems(this.subjects);

    }

    /*
     * *************************************************************************
     * *
     * Properties *
     * *
     * ************************************************************************.
     */

    /*
     * (non-Javadoc)
     * @see org.xine.schedules.builder.fx.gui.ContentController#getRootNode()
     */
    @Override
    public Node getRootNode() {
        return this.root;
    }

    // /

}
