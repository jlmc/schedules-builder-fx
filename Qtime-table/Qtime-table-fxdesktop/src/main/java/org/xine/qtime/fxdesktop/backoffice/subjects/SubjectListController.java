/*
 * Copyright (c) 2015 Qxine <https://github.com/jlmc>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */

package org.xine.qtime.fxdesktop.backoffice.subjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xine.qtime.client.connector.SubjectConnector;
import org.xine.qtime.entities.Subject;
import org.xine.qtime.fxdesktop.backoffice.utils.ActionsTableCell;
import org.xine.qtime.fxdesktop.controllers.StateController;
import org.xine.qtime.fxdesktop.services.SimpleService;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javax.inject.Inject;

/**
 * The Class SubjectListController.
 */
public class SubjectListController extends StateController<Subject> {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectListController.class);

    /**
     * The resources. ResourceBundle that was given to the FXMLLoader.
     */
    @FXML
    private ResourceBundle resources;

    /**
     * The location. URL location of the FXML file that was given to the
     * FXMLLoader.
     */
    @FXML
    private URL location;

    /** The search button. */
    @FXML
    private Button searchButton;

    /** The search text. */
    @FXML
    private TextField searchText;

    /** The name column. */
    @FXML
    private TableColumn<Subject, String> nameColumn;

    /** The actions column. */
    @FXML
    private TableColumn<Subject, Subject> actionsColumn;

    /** The root. */
    @FXML
    private AnchorPane root;

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

    /** The glass pane. */
    @FXML
    private BorderPane glassPane;

    /* *************************************************
     * Services properties
     * *************************************************
     */

    /** The subject connector. */
    @Inject
    private SubjectConnector subjectConnector;

    /** The load subjects. */
    @Inject
    private SimpleService<ObservableList<Subject>> loadSubjects;

    /** The delete service. */
    @Inject
    private SimpleService<Void> deleteService;

    /* *************************************************
     * MODEL properties
     * *************************************************
     */

    /** The subjects. */
    private final ListProperty<Subject> subjects = new SimpleListProperty<>(
            FXCollections.observableArrayList());

    /** The busy. */
    private SimpleBooleanProperty busy;

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.controllers.ContentController#getRootNode()
     */
    /**
     * Gets the root node.
     * @return the root node
     */
    @Override
    public Node getRootNode() {
        return this.root;
    }

    /**
     * Gets the list property.
     * @return the list property
     */
    public ListProperty<Subject> getListProperty() {
        return this.subjects;
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {

        //
        this.busy = new SimpleBooleanProperty();
        this.glassPane.visibleProperty().bind(this.busy);

        this.nameColumn.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));

        this.descriptionColumn.setCellValueFactory(cdf -> new SimpleStringProperty(cdf.getValue()
                .getDescription()));
        this.actionsColumn.setCellValueFactory(cdf -> new SimpleObjectProperty<>(cdf.getValue()));
        this.actionsColumn.setCellFactory(tc -> {
            final ActionsTableCell<Subject, Subject> cell = new ActionsTableCell<>(false);
            cell.setOnDeleteAction(e -> delete(cell.getData()));
            cell.setOnEditAction(e -> edit(cell.getData()));
            return cell;
        });

        this.table.setItems(getListProperty());

        // /
        this.createButton.setOnAction(e -> getMachineStatesController().setActiveController(
                getMachineStatesController().getCreateController()));

        this.searchButton.setOnAction(e -> search());

        // / define the service
        getListProperty().bind(this.loadSubjects.valueProperty());
        this.busy.bind(this.loadSubjects.runningProperty());

    }

    /**
     * Edits the.
     * @param data
     *            the data
     */
    private void edit(final Subject data) {
        LOGGER.info("edit:  " + data.getName());

        setSelected(data);

        getMachineStatesController().setActiveController(
                getMachineStatesController().getEditController());
    }

    /**
     * Delete.
     * @param s
     *            the s
     */
    private void delete(final Subject s) {
        LOGGER.info("delete: " + s.getName());

        this.deleteService.setOnFailed(e -> {
            final Throwable ouch = this.deleteService.getException();
            LOGGER.error(ouch.getClass().getName() + " -> " + ouch.getMessage());
        });

        this.deleteService.setOnSucceeded(e -> {
            LOGGER.info("{} - {} deleted", s.getId(), s.getName());
            Platform.runLater(() -> this.subjects.remove(s));
        });

        this.deleteService.execute(() -> {
            this.subjectConnector.delete(Integer.valueOf(s.getId().intValue()));
            return null;
        }, getApplicationController().getExecuterService());

    }

    /**
     * Search.
     */
    private void search() {
        LOGGER.info("Logger operation search");
        this.loadSubjects.execute(
                () -> FXCollections.observableArrayList(this.subjectConnector.list()),
                getApplicationController().getExecuterService());
    }

    /**
     * Adds the.
     * @param objs
     *            the objs
     */
    @Override
    public void add(final Collection<Subject> objs) {
        if (objs != null) {
            this.subjects.addAll(objs);
        }
    }

    /**
     * Removes the.
     * @param objs
     *            the objs
     */
    @Override
    public void remove(final Collection<Subject> objs) {
        if (objs != null) {
            this.subjects.removeAll(objs);
        }
    }

}
