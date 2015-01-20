package org.xine.qtime.fxdesktop.backoffice.subjects;

import org.xine.qtime.client.connector.SubjectConnector;
import org.xine.qtime.fxdesktop.controllers.StateController;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

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

	/**
	 * The resources.
	 * ResourceBundle that was given to the FXMLLoader.
	 */
	@FXML
	private ResourceBundle resources;

	/**
	 * The location.
	 * URL location of the FXML file that was given to the FXMLLoader.
	 */
	@FXML
	private URL location;

	/**
	 * The cancel button.
	 * Value injected by FXMLLoader.
	 */
	@FXML
	private Button cancelButton;

	/**
	 * The root.
	 * Value injected by FXMLLoader.
	 */
	@FXML
	private AnchorPane root;

	/**
	 * The back button.
	 * Value injected by FXMLLoader.
	 */
	@FXML
	// fx:id="backButton"
	private Button backButton; // Value injected by FXMLLoader

	/**
	 * The name field.
	 * Value injected by FXMLLoader.
	 */
	@FXML
	// fx:id="nameField"
	private TextField nameField; // Value injected by FXMLLoader

	/**
	 * The description field.
	 * Value injected by FXMLLoader.
	 */
	@FXML
	// fx:id="descriptionField"
	private TextArea descriptionField; // Value injected by FXMLLoader

	/**
	 * The title.
	 * Value injected by FXMLLoader.
	 */
	@FXML
	private Label title;

	/**
	 * The save button. fx:id="saveButton"
	 * Value injected by FXMLLoader
	 **/
	@FXML
	private Button saveButton; //
	
	@Inject
	private SubjectConnector subjectConnector;

	/**
	 * Initialize.
	 * This method is called by the FXMLLoader when initialization is complete
	 */
	@FXML
	void initialize() {
		this.backButton.setOnAction(e -> getMachineStatesController().setActiveController(
				getMachineStatesController().getListController()));
		
		
	}

	

	/*
	 * (non-Javadoc)
	 * @see org.xine.qtime.fxdesktop.controllers.ContentController#getRootNode()
	 */
	@Override
	public Node getRootNode() {
		return this.root;
	}

}
