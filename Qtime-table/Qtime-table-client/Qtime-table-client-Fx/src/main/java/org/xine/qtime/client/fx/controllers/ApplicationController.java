package org.xine.qtime.client.fx.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import org.xine.fx.guice.GuiceFXMLLoader;
import org.xine.fx.guice.GuiceFXMLLoader.Result;
import org.xine.qtime.client.fx.gui.FxDecorateScene;
import org.xine.qtime.client.fx.gui.Views;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ApplicationController {

	private static AtomicInteger threadCount = new AtomicInteger(0);

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Rectangle navigationBackground;

	@FXML
	private HBox navigationHBox;

	@FXML
	private HBox navigationButtons;

	@FXML
	private AnchorPane root;

	@FXML
	private ImageView xineLogo;

	@FXML
	private AnchorPane content;

	@Inject
	private GuiceFXMLLoader fxmlLoader;

	// private final List<ContentController> controllers = new ArrayList<>();
	//
	// private NotificationController notificationController;
	//
	// private ContentController activeController = null;

	private final List<ContentController> controllers = new ArrayList<>();

	private NotificationController notificationController;

	private ContentController activeController = null;

	private FxDecorateScene fxDecorateScene;

	private final ExecutorService executerService = Executors
			.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), r -> {
				final Thread t = new Thread(r);
				t.setName("Executor #" + threadCount.getAndIncrement());
				t.setDaemon(true);
				return t;
			});

	private final Set<String> subViews = new LinkedHashSet<String>() {
		private static final long serialVersionUID = 1L;

		{
			add(Views.START_UP);
			add(Views.BACKOFFICE);
		}
	};

	@FXML
	public void initialize() {
		this.navigationBackground.widthProperty().bind(this.root.widthProperty());
		loadSubControllers();
	}

	private void loadSubControllers() {
		this.subViews.forEach(subview -> loadSubController(subview));

		if (this.controllers != null && !this.controllers.isEmpty()) {
			activateController(this.controllers.get(0));
		}
	}

	private void loadSubController(final String subController) {
		try {
			final Result loadResult = this.fxmlLoader.load(ApplicationController.class.getResource(subController),
					this.resources);
			final ContentController controller = loadResult.getController();

			this.controllers.add(controller);
			controller.setApplicationController(this);

			final Button btn = addControllerButton(controller);
			controller.setNavigationButton(btn);

			this.content.getChildren().add(controller.getRootNode());
			controller.setControllerConstrains();

			controller.getRootNode().setVisible(false);

		} catch (final Exception e) {
			throw new RuntimeException("Could not load sub view: " + subController, e);
		}
	}

	private Button addControllerButton(final ContentController controller) {
		final Button navButton = new Button(controller.getName());

		navButton.getStyleClass().add("nav-button");
		navButton.getStyleClass().add("xine-button");
		navButton.setPrefHeight(49);

		navButton.setOnAction(e -> activateController(controller));

		this.navigationButtons.getChildren().add(navButton);

		return navButton;
	}

	private void activateController(final ContentController contentController) {
		activateController(contentController, true);
	}

	private void activateController(final ContentController contentController, final boolean animated) {
		if (this.activeController == contentController) {
			return;
		}
		final int from = this.controllers.indexOf(this.activeController);
		final int to = this.controllers.indexOf(contentController);
		final ContentController oldController = this.activeController;
		contentController.getRootNode().setVisible(true);
		if (this.activeController != null) {
			this.activeController.getRootNode().setVisible(false);
			this.activeController.onDeactivate();
			this.activeController.getNavigationButton().getStyleClass().remove("selected");
		}
		this.activeController = contentController;
		this.activeController.getNavigationButton().getStyleClass().add("selected");
		final int direction = from < to ? -1 : 1;
		if (animated && oldController != null) {
			animateController(contentController, oldController, direction);
		}
		this.activeController.onActivate();
	}

	private void animateController(final ContentController contentController, final ContentController oldController,
			final int direction) {
		oldController.getRootNode().setVisible(true);
		if (this.activeController.getRootNode() instanceof Pane) {
			((Pane) this.activeController.getRootNode()).setPrefSize(this.content.getWidth(), this.content.getHeight());
		}
		if (oldController.getRootNode() instanceof Pane) {
			((Pane) oldController.getRootNode()).setPrefSize(this.content.getWidth(), this.content.getHeight());
		}
		AnchorPane.clearConstraints(this.activeController.getRootNode());
		AnchorPane.clearConstraints(oldController.getRootNode());
		final Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		KeyValue kvNew = new KeyValue(this.activeController.getRootNode().layoutXProperty(),
				this.content.getWidth() * -direction);
		KeyValue kvNewOpac = new KeyValue(this.activeController.getRootNode().opacityProperty(), 0.0);
		KeyFrame kf = new KeyFrame(Duration.ZERO, kvNew, kvNewOpac);
		timeline.getKeyFrames().add(kf);
		final KeyValue kvOld = new KeyValue(oldController.getRootNode().layoutXProperty(),
				this.content.getWidth() * direction);
		kvNew = new KeyValue(this.activeController.getRootNode().layoutXProperty(), 0);
		kvNewOpac = new KeyValue(this.activeController.getRootNode().opacityProperty(), 1.0);
		final KeyValue kvOldOpac = new KeyValue(oldController.getRootNode().opacityProperty(), 0.0);
		kf = new KeyFrame(Duration.millis(200), kvOld, kvNew, kvNewOpac, kvOldOpac);
		timeline.getKeyFrames().add(kf);
		timeline.play();

		timeline.setOnFinished(e -> {
			oldController.setControllerConstrains();
			contentController.setControllerConstrains();
			oldController.getRootNode().setVisible(false);
		});

	}

	public List<ContentController> getControllers() {
		return this.controllers;
	}

	public void onQuit() {
		this.controllers.forEach(controller -> {
			try {
				onQuit();
			} catch (final Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ExecutorService getExecuterService() {
		return this.executerService;
	}

	public NotificationController getNotificationController() {
		return this.notificationController;
	}

	public void setDecorateScene(final FxDecorateScene fxDecorateScene) {
		this.fxDecorateScene = fxDecorateScene;

		fxDecorateScene.getController().addMoveNode(this.navigationBackground);
		fxDecorateScene.getController().addMoveNode(this.xineLogo);
	}

	public FxDecorateScene getDecorateScene() {
		return this.fxDecorateScene;
	}

}
