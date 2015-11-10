package org.xine.qtime.client.fx.controllers.office;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import org.xine.fx.guice.GuiceFXMLLoader;
import org.xine.fx.guice.GuiceFXMLLoader.Result;
import org.xine.qtime.client.fx.controllers.ContentController;
import org.xine.qtime.client.fx.gui.Views;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class OfficeController extends ContentController {
	AtomicInteger count = new AtomicInteger(0);

	@Inject
	private GuiceFXMLLoader fxmlLoader;

	@FXML
	private AnchorPane root;

	@FXML
	private VBox nav;

	@FXML
	private AnchorPane component;

	@FXML
	private VBox navItems;

	public OfficeController() {
		super.setName("Office");
	}

	private final Map<String, String> optionsViews = Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put("Disciplinas", Views.OFFICE_CONTEINER_VIEW);
			put("Professores", "/fxml/OfficeSubConteinerView2.fxml");
		}
	});

	private final List<ContentController> controllers = new ArrayList<>();

	private ContentController activeController;

	@FXML
	public void initialize() {

		// on the firts

	}

	@Override
	public void onActivate() {

		// Callback - design for extension
		if (isFirtsActivation()) {
			// loadContents();
			;
		}

		this.count.incrementAndGet();
	}

	private boolean isFirtsActivation() {
		return this.count.get() <= 0;
	}

	private void loadContents() {
		if (this.component.getChildren().isEmpty()) {
			// if( getApplicationController() != null){
			// getApplicationController().getExecutorService().submit(() -> {

			try {

				loadSubControllers();

				if (this.controllers != null && !this.controllers.isEmpty() && this.controllers.get(0) != null) {
					activeController(this.controllers.get(0), true);

				}

			} catch (final Exception e) {
				System.out.println(e);
			}
			// return null;
			//
			// });
			// }

		}
	}

	private void activeController(final ContentController subController, final boolean animated) {

		if (this.activeController == subController) {
			return;
		}

		final int from = this.controllers.indexOf(this.activeController);
		final int to = this.controllers.indexOf(subController);

		final ContentController oldController = this.activeController;

		subController.getRootNode().setVisible(true);

		if (oldController != null) {
			oldController.getRootNode().setVisible(false);
			oldController.onDeactivate();
			oldController.getNavigationButton().getStyleClass().remove("selected");
		}

		this.activeController = subController;
		this.activeController.getNavigationButton().getStyleClass().add("selected");

		final int direction = from < to ? -1 : 1;
		if (animated && oldController != null) {
			animateController(subController, oldController, direction);
		}

		this.activeController.onActivate();

	}

	private void animateController(final ContentController subController, final ContentController oldController,
			final int direction) {
		if (oldController != null) {
			oldController.getRootNode().setVisible(false);
			oldController.onDeactivate();
			oldController.getNavigationButton().getStyleClass().remove("selected");
		}

		this.activeController = subController;
		this.activeController.getNavigationButton().getStyleClass().add("selected");

	}

	private void loadSubControllers() throws IOException {
		for (final String key : this.optionsViews.keySet()) {
			final ContentController controller = loadSubController(this.optionsViews.get(key));
			if (controller != null) {

				this.controllers.add(controller);

				this.component.getChildren().add(controller.getRootNode());
				controller.getRootNode().setVisible(false);

				controller.setName(key);

				////
				final Button buttonNav = new Button(key);
				buttonNav.getStyleClass().add("nav-button");
				buttonNav.getStyleClass().add("xine-button");
				buttonNav.setPrefHeight(49);
				buttonNav.setOnAction(e -> {
					activeController(controller);
				});

				////

				controller.setNavigationButton(buttonNav);
				this.navItems.getChildren().add(controller.getNavigationButton());

				// this.component.getChildren().add(controller.getRootNode());
			}
		}

	}

	private void activeController(final ContentController controller) {
		activeController(controller, true);

	}

	private ContentController loadSubController(final String viewPath) {
		try {
			ContentController controller;
			Result loadResult;
			loadResult = this.fxmlLoader.load(OfficeController.class.getResource(viewPath));

			controller = loadResult.getController();
			controller.setControllerConstrains();
			// controller.getRootNode().getStyleClass().add("bodyHBox");

			return controller;
		} catch (final IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.xine.schedule.fx.desktop.controllers.ContentController#getRootNode()
	 */
	@Override
	public Node getRootNode() {
		return this.root;
	}

}

