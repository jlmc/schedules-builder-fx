package org.xine.schedules.builder.fx.controllers;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import javax.inject.Inject;

import org.xine.fx.guice.GuiceFXMLLoader;
import org.xine.fx.guice.GuiceFXMLLoader.Result;
import org.xine.schedules.builder.fx.gui.ApplicationController;
import org.xine.schedules.builder.fx.gui.ContentController;
import org.xine.schedules.builder.fx.gui.ControlledScreen;
import org.xine.schedules.builder.fx.gui.FxDecorateScene;
import org.xine.schedules.builder.fx.gui.ScreensController;
import org.xine.schedules.builder.fx.gui.Views;

/**
 * The Class QApplicationController.
 */
public class QApplicationController implements ControlledScreen, ApplicationController {

	/** The screens controller. */
	// private ScreensController screensController;

	private static AtomicInteger threadCount = new AtomicInteger(0);

	/** The navigation background. */
	@FXML
	private Rectangle navigationBackground;

	@FXML
	private HBox navigationHBox;

	/** The navigation buttons. */
	@FXML
	private HBox navigationButtons;

	/** The root. */
	@FXML
	private AnchorPane root;

	@FXML
	private ImageView spoutLogo;

	/** The content. */
	@FXML
	private AnchorPane content;

	/** The fxml loader. */
	@Inject
	private GuiceFXMLLoader fxmlLoader;

	/** The executer service. */
	@SuppressWarnings("unused")
	private final ExecutorService executerService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), new ThreadFactory() {
		@SuppressWarnings("synthetic-access")
		@Override
		public Thread newThread(final Runnable r) {
			final Thread t = new Thread(r);
			t.setName("Executor #" + threadCount.getAndIncrement());
			t.setDaemon(true);
			return t;
		}
	});

	/** The sub views. Sub views of the Application */
	private final Set<String> subViews = new LinkedHashSet<String>() {
		private static final long serialVersionUID = 1L;
		{
			add(Views.START_UP);
			add(Views.OFFICE_VIEW);
			add(Views.CONFIGURATIONS);
		}
	};

	/** The controllers. */
	private final List<ContentController> controllers = new ArrayList<ContentController>();

	/** The active controller. */
	private ContentController activeController = null;

	/** The fx decorate scene. */
	@SuppressWarnings("unused")
	private FxDecorateScene fxDecorateScene;

	/**
	 * Initialize.
	 */
	@FXML
	public void initialize() {
		this.navigationBackground.widthProperty().bind(this.root.widthProperty());

		loadSubControllers();
	}

	/**
	 * Load sub controllers.
	 */
	private void loadSubControllers() {
		for (final String subview : this.subViews) {
			loadSubController(subview);
		}

		if (this.controllers != null && !this.controllers.isEmpty()) {
			activateController(this.controllers.get(0));
		}
	}

	/**
	 * Load sub controller.
	 * @param subController
	 *            the sub controller
	 */
	private void loadSubController(final String subController) {
		try {
			final Result loadResult = this.fxmlLoader.load(ApplicationController.class.getResource(subController));
			final ContentController controller = loadResult.getController();

			this.controllers.add(controller);
			controller.setApplicationController(this);

			//
			addControllerButton(controller);
			//

			this.content.getChildren().add(controller.getRootNode());
			setControllerConstrains(controller);

			controller.getRootNode().setVisible(false);

		} catch (final Exception e) {
			throw new RuntimeException("Could not load sub view: " + subController, e);
		}

	}

	/**
	 * Adds the controller button.
	 * @param controller
	 *            the controller
	 */
	private void addControllerButton(final ContentController controller) {
		final Button navButton = new Button(controller.getName());
		// navButton.getStyleClass().add("nav-button");
		navButton.setPrefHeight(49);

		//navButton.setOnAction(e -> activateController(controller));
		navButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				activateController(controller);
			}
		});

		controller.setNavigationButton(navButton);

		//
		this.navigationButtons.getChildren().add(navButton);
	}

	/**
	 * Sets the controller constrains.
	 * @param controller
	 *            the new controller constrains
	 */
	@SuppressWarnings({"static-method", "boxing" })
	protected void setControllerConstrains(final ContentController controller) {
		AnchorPane.setTopAnchor(controller.getRootNode(), 0d);
		AnchorPane.setLeftAnchor(controller.getRootNode(), 0d);
		AnchorPane.setRightAnchor(controller.getRootNode(), 0d);
		AnchorPane.setBottomAnchor(controller.getRootNode(), 0d);
	}

	/**
	 * Activate controller.
	 * @param contentController
	 *            the content controller
	 */
	private void activateController(final ContentController contentController) {
		activateController(contentController, true);

	}

	/**
	 * Activate controller.
	 * @param contentController
	 *            the content controller
	 * @param animate
	 *            the animate
	 */
	private void activateController(final ContentController contentController, final boolean animate) {
		if (contentController == this.activeController) {
			return;
		}

		final int from = this.controllers.indexOf(this.activeController);
		final int to = this.controllers.indexOf(contentController);

		final ContentController oldActiveController = this.activeController;
		contentController.getRootNode().setVisible(true);

		if (oldActiveController != null) {
			oldActiveController.onDeactivate();
			oldActiveController.getRootNode().setVisible(false);
			oldActiveController.getNavigationButton().getStyleClass().remove("selected");
		}

		this.activeController = contentController;
		this.activeController.getNavigationButton().getStyleClass().add("selected");

		if (animate && oldActiveController != null) {
			final int direction = from < to ? -1 : 1;
			animateController(this.activeController, oldActiveController, direction);
		}

		this.activeController.onActivate();

	}

	/**
	 * Animate controller.
	 * @param contentController
	 *            the content controller
	 * @param oldController
	 *            the old controller
	 * @param direction
	 *            the direction
	 */
	@SuppressWarnings("boxing")
	private void animateController(final ContentController contentController, final ContentController oldController, final int direction) {
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
		KeyValue kvNew = new KeyValue(this.activeController.getRootNode().layoutXProperty(), this.content.getWidth() * -direction);
		KeyValue kvNewOpac = new KeyValue(this.activeController.getRootNode().opacityProperty(), 0.0);
		KeyFrame kf = new KeyFrame(Duration.ZERO, kvNew, kvNewOpac);
		timeline.getKeyFrames().add(kf);
		final KeyValue kvOld = new KeyValue(oldController.getRootNode().layoutXProperty(), this.content.getWidth() * direction);
		kvNew = new KeyValue(this.activeController.getRootNode().layoutXProperty(), 0);
		kvNewOpac = new KeyValue(this.activeController.getRootNode().opacityProperty(), 1.0);
		final KeyValue kvOldOpac = new KeyValue(oldController.getRootNode().opacityProperty(), 0.0);
		kf = new KeyFrame(Duration.millis(200), kvOld, kvNew, kvNewOpac, kvOldOpac);
		timeline.getKeyFrames().add(kf);
		timeline.play();



		//        timeline.setOnFinished((final ActionEvent arg0) -> {
		//            setControllerConstrains(oldController);
		//            setControllerConstrains(contentController);
		//            oldController.getRootNode().setVisible(false);
		//
		//        });

		timeline.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent arg0) {
				setControllerConstrains(oldController);
				setControllerConstrains(contentController);
				oldController.getRootNode().setVisible(false);
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * @see org.xine.schedules.builder.fx.gui.ControlledScreen#setScreenParent(org.xine.schedules.builder.fx.gui.ScreensController)
	 */
	@Override
	public void setScreenParent(final ScreensController screenPage) {
		// this.screensController = screenPage;
	}

	/*
	 * (non-Javadoc)
	 * @see org.xine.schedules.builder.fx.gui.ApplicationController#setDecorateScene(org.xine.schedules.builder.fx.gui.FxDecorateScene)
	 */
	@Override
	public void setDecorateScene(final FxDecorateScene fxDecorateScene) {
		this.fxDecorateScene = fxDecorateScene;
	}

	/**
	 * On quit.
	 */
	@Override
	public void onQuit() {

		//		this.controllers.parallelStream().forEach((final ContentController t) -> {
		//			try {
		//				t.onQuit();
		//			} catch (final Exception e) {
		//				e.printStackTrace();
		//			}
		//		});

		for (final ContentController controller : this.controllers) {
			try {
				controller.onQuit();
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}
}
