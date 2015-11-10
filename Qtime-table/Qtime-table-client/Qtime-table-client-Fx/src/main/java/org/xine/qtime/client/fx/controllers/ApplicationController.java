package org.xine.qtime.client.fx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import org.xine.fx.guice.GuiceFXMLLoader;
import org.xine.fx.guice.GuiceFXMLLoader.Result;
import org.xine.qtime.client.fx.gui.FxDecorateScene;
import org.xine.qtime.client.fx.gui.Views;
import org.xine.qtime.client.fx.gui.font.FontAwesomeDecorate;
import org.xine.qtime.client.fx.gui.icons.Icons;
import org.xine.qtime.client.fx.model.User;
import org.xine.qtime.client.fx.utils.concurrence.ThreadFactoryBuilder;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The Class ApplicationController.
 */
public class ApplicationController {

	private User model;

	/** The executor service. */
	private final ExecutorService executorService = Executors.newFixedThreadPool(
			Runtime.getRuntime().availableProcessors(),
			new ThreadFactoryBuilder().setNamePrefix("E-Plan-").setDaemon(true).build());

	@Inject
	private GuiceFXMLLoader fxmlLoader;

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
	private ImageView spoutLogo;

	@FXML
	private Label username;

	@FXML
	private ImageView avatar;

	@FXML
	private Label avatarDefault;

	@FXML
	private Button notifications;

	@FXML
	private AnchorPane content;


	/** The sub views. Sub views of the Application */
	private final Set<String> subViews = new LinkedHashSet<String>() {
		private static final long serialVersionUID = 1L;

		{
			add(Views.OFFICE_VIEW);
			add(Views.WEB_VIEW);
			add(Views.DASHBOARD_VIEW);
		}
	};

	private FxDecorateScene fxDecorateScene;

	private final List<ContentController> controllers = new ArrayList<>();

	private ContentController activeController;

	private NotificationController notificationController;

	@FXML
	public void initialize() {
		// init Model
		this.model = new User();

		this.navigationBackground.widthProperty().bind(this.root.widthProperty());

		// load sub views
		loadSubControllers();

		this.username.textProperty().bind(this.model.usernameProperty());
		this.avatar.imageProperty().bind(this.model.avatarProperty());

		FontAwesomeDecorate.stylise(this.avatarDefault);

		// this.avatarDefault.setGraphic(Test.getLabel());
		this.avatarDefault.setGraphic(Icons.getIcon(Icons.USER, 35, 35));
	}

	private void loadSubControllers() {
		this.subViews.forEach(view -> loadSubController(view));

		final ContentController firtsSubController = (this.controllers != null) && !this.controllers.isEmpty()
				? this.controllers.get(0) : null;

		if (firtsSubController != null) {
			activateController(firtsSubController);
		}
	}

	private void activateController(final ContentController subController) {
		activateController(subController, true);
	}

	private void activateController(final ContentController subController, final boolean animated) {
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
		if (animated && (oldController != null)) {
			animateController(subController, oldController, direction);
		}

		this.activeController.onActivate();
	}


	private void animateController(final ContentController newController, final ContentController oldController,
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
			newController.setControllerConstrains();
			oldController.getRootNode().setVisible(false);
		});
	}

	public void setDecorateScene(final FxDecorateScene fxDecorateScene) {
		this.fxDecorateScene = fxDecorateScene;

		fxDecorateScene.getController().addMoveNode(this.navigationBackground);
		fxDecorateScene.getController().addMoveNode(this.spoutLogo);
		fxDecorateScene.getController().addMoveNode(this.username);
		fxDecorateScene.getController().addMoveNode(this.avatar);
	}

	public FxDecorateScene getDecorateScene() {
		return this.fxDecorateScene;
	}

	private void loadSubController(final String view) {
		try {
			final Result loadResult = this.fxmlLoader.load(ApplicationController.class.getResource(view));

			final ContentController controller = loadResult.getController();
			this.controllers.add(controller);
			controller.setApplicationController(this);
			final Button btn = addControllerButton(controller);
			controller.setNavigationButton(btn);
			this.content.getChildren().add(controller.getRootNode());

			controller.setControllerConstrains();

			controller.getRootNode().setVisible(false);
		} catch (final IOException e) {
			throw new RuntimeException(String.format("Could not load sub view: %s ", view), e);
		}
	}

	private Button addControllerButton(final ContentController controller) {
		final Button nav = new Button(controller.getName());
		nav.getStyleClass().add("nav-button");
		nav.getStyleClass().add("xine-button");
		nav.setPrefHeight(49);

		nav.setOnAction(event -> {
			activateController(controller);
		});

		this.navigationButtons.getChildren().add(nav);

		return nav;
	}

	public void onNotificationButtonPressed() {
		Platform.runLater(() -> {
			final Stage notif = getNotificationController().getStage();
			if (notif.isShowing()) {
				if (!getNotificationController().isAnimating()) {
					getNotificationController().hide();
				}
			}

		});
	}

	public void onNotificationButtonReleased() {
		Platform.runLater(() -> {
			final Stage notif = getNotificationController().getStage();
			if (!notif.isShowing()) {
				final Bounds b = this.notifications.localToScene(this.notifications.getLayoutBounds());

				double x = (b.getMinX() + b.getMaxX()) / 2d;
				double y = b.getMaxY();

				x += getDecorateScene().getStage().getX();
				y += getDecorateScene().getStage().getY();

				final Point2D arrow = getNotificationController().getArrowPosition();
				x -= arrow.getX();
				y -= arrow.getY();

				notif.setX(x);
				notif.setY(y);

				getNotificationController().show();
			}
		});

	}

	public NotificationController getNotificationController() {
		return this.notificationController;
	}

	public void setNotificationController(final NotificationController notificationController) {
		this.notificationController = notificationController;
	}

	public ExecutorService getExecutorService() {
		return this.executorService;
	}



}
