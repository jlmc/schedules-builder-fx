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
import org.xine.qtime.fxdesktop.controllers.StateController;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * The Class SubjectCreateController.
 */
public class SubjectCreateController extends StateController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SubjectCreateController.class);

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

	/**
	 * The cancel button. Value injected by FXMLLoader.
	 */
	@FXML
	private Button cancelButton;

	/**
	 * The root. Value injected by FXMLLoader.
	 */
	@FXML
	private AnchorPane root;

	/**
	 * The back button. Value injected by FXMLLoader.
	 */
	@FXML
	// fx:id="backButton"
	private Button backButton; // Value injected by FXMLLoader

	/**
	 * The name field. Value injected by FXMLLoader.
	 */
	@FXML
	// fx:id="nameField"
	private TextField nameField; // Value injected by FXMLLoader

	/**
	 * The description field. Value injected by FXMLLoader.
	 */
	@FXML
	// fx:id="descriptionField"
	private TextArea descriptionField; // Value injected by FXMLLoader

	/**
	 * The title. Value injected by FXMLLoader.
	 */
	@FXML
	private Label title;

	/**
	 * The save button. fx:id="saveButton" Value injected by FXMLLoader
	 **/
	@FXML
	private Button saveButton; //

	/** The subject connector. */
	@Inject
	private SubjectConnector subjectConnector;

	private BooleanProperty isBusy = new SimpleBooleanProperty(false);

	/**
	 * Initialize. This method is called by the FXMLLoader when initialization
	 * is complete
	 */
	@FXML
	void initialize() {
		this.backButton.setOnAction(e -> getMachineStatesController()
				.setActiveController(
						getMachineStatesController().getListController()));
		this.saveButton.setOnAction(e -> save());

	}

	/**
	 * Save.
	 */
	private void save() {
		//super.getApplicationController().getExecuterService().execute(
		Platform.runLater(() -> {
			this.isBusy.set(true);

			Subject subject = new Subject(null, this.nameField.getText(),
					this.nameField.getText(), this.descriptionField.getText());

			try {

				this.subjectConnector.create(subject);

				// TODO:: missing handler o response
				
				
				
				this.nameField.setText("");
				this.descriptionField.setText("");

				LOGGER.info("Subject saved with sucess");
				getMachineStatesController().setActiveController(
						getMachineStatesController().getListController());

			} catch (RuntimeException e) {
				LOGGER.error(e.getMessage());
			} finally {
				this.isBusy.set(false);
			}

		});
		// missing validation
		//Platform.runLater();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xine.qtime.fxdesktop.controllers.ContentController#getRootNode()
	 */
	@Override
	public Node getRootNode() {
		return this.root;
	}

}
