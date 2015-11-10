package org.xine.qtime.client.fx.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.xine.fx.guice.GuiceFXMLLoader;
import org.xine.fx.guice.GuiceFXMLLoader.Result;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public abstract class DecoraterController extends ContentController {

	@Inject
	private GuiceFXMLLoader fxmlLoader;

	private final List<ContentController> subcontrollers = new ArrayList<>();

	private ContentController activeController;

	protected <T> T loadSubView(final String subController,
			final ResourceBundle resources) {
		try {
			final Result loadResult = this.fxmlLoader.load(
					getClass().getResource(subController),
					resources);
			final T controller = loadResult.getController();
			return controller;
		} catch (final IOException e) {
			throw new RuntimeException("Can't Load the subcontroller "
					+ subController + " caused by: " + e);
		}
	}

	public void loadsubviews(final Collection<String> subviews,
			final ResourceBundle resources) {
		if (subviews != null) {
			for (final String subview : subviews) {
				if (subview != null) {
					loadSubController(subview, resources);
				}
			}
		}

		if (this.subcontrollers != null && !this.subcontrollers.isEmpty()) {
			activateController(this.subcontrollers.get(0));
		}

	}

	private void loadSubController(final String subController,
			final ResourceBundle resources) {
		try {
			final ContentController controller = loadSubView(subController,
					resources);
			this.subcontrollers.add(controller);

			controller.setApplicationController(getApplicationController());

			final Button btn = addControllerButton(controller);
			controller.setNavigationButton(btn);

			if (getComponent() != null) {
				getComponent().getChildren().add(controller.getRootNode());
				controller.setControllerConstrains();
				controller.getRootNode().setVisible(false);
			}

		} catch (final Exception e) {
		}
	}

	private Button addControllerButton(final ContentController controller) {
		final Button navButton = new Button(controller.getName());
		navButton.setPrefWidth(200.0D);
		navButton.setMinWidth(200.0D);
		navButton.setMaxWidth(200.0D);

		navButton.setOnAction(e -> activateController(controller));

		if (getnavItems() != null) {
			getnavItems().getChildren().add(navButton);
		}

		return navButton;
	}

	private void activateController(final ContentController controller) {
		activateController(controller, false);
	}

	private void activateController(final ContentController contentController,
			final boolean animate) {
		if (this.activeController == contentController) {
			return;
		}

		final int from = this.subcontrollers.indexOf(this.activeController);
		final int to = this.subcontrollers.indexOf(contentController);
		final ContentController oldController = this.activeController;
		contentController.getRootNode().setVisible(true);
		if (this.activeController != null) {
			this.activeController.getRootNode().setVisible(false);
			this.activeController.onDeactivate();
			this.activeController.getNavigationButton().getStyleClass()
					.remove("selected");
		}
		this.activeController = contentController;
		this.activeController.getNavigationButton().getStyleClass()
				.add("selected");
		final int direction = from < to ? -1 : 1;
		if (animate && oldController != null) {
			animateController(contentController, oldController, direction);
		}
		this.activeController.onActivate();
	}

	private void animateController(final ContentController contentController,
			final ContentController oldController, final int direction) {
	}

	@Override
	public void onQuit() {
		this.subcontrollers.forEach(controller -> {
			try {
				controller.onQuit();
			} catch (final Exception e) {
			}
		});
		super.onQuit();

	}

	@Override
	public void setApplicationController(
			ApplicationController applicationController) {
		super.setApplicationController(applicationController);

		this.subcontrollers.parallelStream().forEach(
				contentController -> contentController
						.setApplicationController(applicationController));

	}

	public abstract Pane getComponent();

	public abstract Pane getnavItems();

}