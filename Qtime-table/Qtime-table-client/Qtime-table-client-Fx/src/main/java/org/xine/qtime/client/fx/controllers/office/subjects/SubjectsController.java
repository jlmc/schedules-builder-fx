package org.xine.qtime.client.fx.controllers.office.subjects;

import org.xine.fx.guice.FXMLController;
import org.xine.qtime.client.fx.controllers.ContentController;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

@FXMLController
public class SubjectsController extends ContentController {

	@FXML
	private AnchorPane root;

	@FXML
	private AnchorPane list;

	@Override
	public Node getRootNode() {
		return this.root;
	}

	public SubjectsController() {
		super();
		super.setName("subjects");
	}

	@FXML
	public void initialize() {
		System.out.println("" + this.list == null);

		AnchorPane.setTopAnchor(this.list, Double.valueOf(0d));
		AnchorPane.setLeftAnchor(this.list, Double.valueOf(0d));
		AnchorPane.setRightAnchor(this.list, Double.valueOf(0d));
		AnchorPane.setBottomAnchor(this.list, Double.valueOf(0d));

	}



}
