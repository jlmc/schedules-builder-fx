package org.xine.qtime.client.fx.controllers.dashboard;

import org.xine.qtime.client.fx.controllers.ContentController;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class DashboardController extends ContentController {

	@FXML
	private AnchorPane root;

	public DashboardController() {
		setName("Dashboard");
	}

	@Override
	public Node getRootNode() {
		return this.root;
	}

	@FXML
	public void initialize() {

	}

}
