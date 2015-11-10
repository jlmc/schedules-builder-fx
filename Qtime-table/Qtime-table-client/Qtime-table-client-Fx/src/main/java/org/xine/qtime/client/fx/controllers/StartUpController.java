package org.xine.qtime.client.fx.controllers;

import java.io.IOException;

import org.xine.fx.guice.FXMLController;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;

@FXMLController
public class StartUpController extends ContentController {
	@FXML
	private AnchorPane root;

	@FXML
	private Button playButton;

	@FXML
	private ProgressIndicator progress;

	@SuppressWarnings("unused")
	private Task<Void> counter;

	public StartUpController() {
		super();
		setName("StartUp");
	}

	@Override
	public Node getRootNode() {
		return this.root;
	}

	@FXML
	public void initialize() {
		this.progress.setVisible(false);
	}

	@FXML
	public void onPlayClicked(final ActionEvent actionEvent) throws IOException {
		this.progress.setVisible(true);
	}

}
