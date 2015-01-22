/*
 * Copyright (c) 2015 Qxine <https://github.com/jlmc>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */

package org.xine.qtime.fxdesktop.backoffice.subjects;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xine.qtime.client.connector.manager.SubjectListTask;
import org.xine.qtime.entities.Subject;
import org.xine.qtime.fxdesktop.backoffice.utils.ActionsTableCell;
import org.xine.qtime.fxdesktop.controllers.StateController;

/**
 * The Class SubjectListController.
 */
public class SubjectListController extends StateController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SubjectListController.class);

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
	 * MODEL properties
	 ***************************************************/
	
	/** The subjects. */
	private final ListProperty<Subject> subjects = new SimpleListProperty<>(
			FXCollections.observableArrayList());


	private SimpleBooleanProperty busy;


	private LoadService service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xine.qtime.fxdesktop.controllers.ContentController#getRootNode()
	 */
	@Override
	public Node getRootNode() {
		return this.root;
	}

	/**
	 * Gets the list property.
	 * 
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
		busy = new SimpleBooleanProperty();
		glassPane.visibleProperty().bind(busy);

		// lastNameCol.setCellValueFactory(new PropertyValueFactory<Person,
		// String>("lastName"));
		this.nameColumn
				.setCellValueFactory(new PropertyValueFactory<Subject, String>(
						"name"));
		// this.nameColumn.setCellValueFactory(cdf -> new
		// SimpleStringProperty(cdf
		// .getValue().getName()));
		this.descriptionColumn
				.setCellValueFactory(cdf -> new SimpleStringProperty(cdf
						.getValue().getDescription()));
		this.actionsColumn
				.setCellValueFactory(cdf -> new SimpleObjectProperty<Subject>(
						cdf.getValue()));
		this.actionsColumn
				.setCellFactory(tc -> {
					final ActionsTableCell<Subject, Subject> cell = new ActionsTableCell<>(
							false);

					cell.setOnDeleteAction(e -> {

						final Subject s = cell.getData();
//						this.table.getSelectionModel().getSelectedItems()
//								.add(s);
						delete(s);
					});

					cell.setOnEditAction(e -> {
						final Subject s = cell.getData();
//						this.table.getSelectionModel().getSelectedItems()
//								.add(s);
						
						cell.getTableRow();
						edit(s);
					});

					return cell;
				});

		this.table.setItems(getListProperty());

		// /
		this.createButton.setOnAction(e -> {

			getMachineStatesController().setActiveController(
					getMachineStatesController().getCreateController());
		});

		this.searchButton.setOnAction(e -> search());

		// ///////////////////
		this.service = new LoadService();
		this.getListProperty().bind(service.valueProperty());
		// this.table.itemsProperty().bind(service.valueProperty());
		this.busy.bind(this.service.runningProperty());
	}

	/**
	 * Edits the.
	 * 
	 * @param data
	 *            the data
	 */
	private void edit(final Subject data) {
		LOGGER.info("edit:  " + data.getName());
	}

	/**
	 * Delete.
	 * 
	 * @param s
	 *            the s
	 */
	private void delete(final Subject s) {
		LOGGER.info("delete: " + s.getName());

	}

	/**
	 * Search.
	 */
	private void search() {

		// TODO Auto-generated method stub
		LOGGER.info("Logger operation not implemented");

		this.service.reset();
		this.service.start();

		//
		// Platform.runLater(() -> {
		// final List<Subject> ss = new ArrayList<>();
		// for (int i = 0; i < 150; i++) {
		// ss.add(new Subject(Long.valueOf(i), "name" + i, "N" + i,
		// "the name " + i));
		// }
		//
		// getListProperty().clear();
		// getListProperty().addAll(ss);
		// this.glassPane.visibleProperty().set(false);
		// });
	}

	private class LoadService extends Service<ObservableList<Subject>> {

		@Override
		protected Task<ObservableList<Subject>> createTask() {
			return new SubjectListTask();
			
//			return new Task<ObservableList<Subject>>() {
//
//				@Override
//				protected ObservableList<Subject> call() throws Exception {
//					// TODO Auto-generated method stub
//					ObservableList<Subject> subjects = FXCollections
//							.observableArrayList();
//					for (int i = 0; i < 200; i++) {
//						subjects.add(new Subject(12L, "Name " + i, "", ""));
//					}
//					return subjects;
//				}
//			};
		}

	}

}
