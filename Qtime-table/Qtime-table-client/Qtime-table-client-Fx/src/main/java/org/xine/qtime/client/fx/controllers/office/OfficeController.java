package org.xine.qtime.client.fx.controllers.office;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.xine.fx.guice.FXMLController;
import org.xine.qtime.client.fx.controllers.DecoraterController;
import org.xine.qtime.client.fx.gui.Views;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

@FXMLController
public class OfficeController extends DecoraterController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane root;

	@FXML
	private VBox nav;

	@FXML
	private AnchorPane component;

	@FXML
	private VBox navItems;

	/**
	 * Instantiates a new back office controller.
	 */
	public OfficeController() {
		super();
		super.setName("Office");
	}

	@Override
	public Node getRootNode() {
		return this.root;
	}

	private final List<String> subviews = new LinkedList<String>() {
		private static final long serialVersionUID = 1L;
		{
			add(Views.BACKOFFICE_SUBJECT);
			add(Views.BACKOFFICE_PERSONS);
		}
	};

	@FXML
	public void initialize() {
		// super.loadsubviews(this.subviews, this.resources);

	}

	@Override
	public Pane getComponent() {
		return this.component;
	}

	@Override
	public Pane getnavItems() {
		return this.navItems;
	}

}
