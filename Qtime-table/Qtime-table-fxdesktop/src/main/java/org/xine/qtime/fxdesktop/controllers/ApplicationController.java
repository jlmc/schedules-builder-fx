package org.xine.qtime.fxdesktop.controllers;

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
import org.xine.qtime.fxdesktop.gui.FxDecorateScene;
import org.xine.qtime.fxdesktop.gui.Views;

/**
 * The Class ApplicationController.
 */
public class ApplicationController {

    /** The screens controller. */
    // private ScreensController screensController;

    private static AtomicInteger threadCount = new AtomicInteger(0);

    /** The navigation background. */
    @FXML
    private Rectangle navigationBackground;

    /** The navigation h box. */
    @FXML
    private HBox navigationHBox;

    /** The navigation buttons. */
    @FXML
    private HBox navigationButtons;

    /** The root. */
    @FXML
    private AnchorPane root;

    /** The spout logo. */
    @FXML
    private ImageView spoutLogo;

    /** The content. */
    @FXML
    private AnchorPane content;

    /** The fxml loader. */
    @Inject
    private GuiceFXMLLoader fxmlLoader;

    /** The controllers. */
    private final List<ContentController> controllers = new ArrayList<>();

    /** The notification controller. */
    @SuppressWarnings("unused")
    private NotificationController notificationController;

    /** The active controller. */
    private ContentController activeController = null;

    /** The fx decorate scene. */
    @SuppressWarnings("unused")
    private FxDecorateScene fxDecorateScene;

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
            add(Views.BACKOFFICE);

        }
    };

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
        this.subViews.forEach(subview -> loadSubController(subview));

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

            final Button btn = addControllerButton(controller);
            controller.setNavigationButton(btn);

            this.content.getChildren().add(controller.getRootNode());
            controller.setControllerConstrains();

            controller.getRootNode().setVisible(false);

        } catch (final Exception e) {
            throw new RuntimeException("Could not load sub view: " + subController, e);
        }
    }

    /**
     * Adds the controller button.
     * @param controller
     *            the controller
     * @return the button
     */
    private Button addControllerButton(final ContentController controller) {
        final Button navButton = new Button(controller.getName());

        navButton.getStyleClass().add("nav-button");
        navButton.getStyleClass().add("xine-button");
        navButton.setPrefHeight(49);

        navButton.setOnAction(e -> activateController(controller));

        this.navigationButtons.getChildren().add(navButton);

        return navButton;
    }

    /**
     * Sets the active Controller in the content area.
     * @param contentController
     *            the new active controller.
     */
    private void activateController(final ContentController contentController) {
        activateController(contentController, true);
    }

    /**
     * Sets the active Controller in the content area.
     * @param contentController
     *            the new active controller.
     * @param animate
     *            wether the change of controllers should be animated.
     */
    private void activateController(final ContentController contentController, final boolean animate) {
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
        if (animate && oldController != null) {
            animateController(contentController, oldController, direction);
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

        timeline.setOnFinished(e -> {
            oldController.setControllerConstrains();
            contentController.setControllerConstrains();
            oldController.getRootNode().setVisible(false);
        });
        //
        // timeline.setOnFinished(new EventHandler<ActionEvent>() {
        // @Override
        // public void handle(final ActionEvent arg0) {
        // oldController.setControllerConstrains();
        // contentController.setControllerConstrains();
        // oldController.getRootNode().setVisible(false);
        // }
        // });

    }

    /**
     * Gets the controllers.
     * @return the controllers
     */
    public List<ContentController> getControllers() {
        return this.controllers;
    }

    /**
     * Sets the decorate scene.
     * @param fxDecorateScene
     *            the new decorate scene
     */
    public void setDecorateScene(final FxDecorateScene fxDecorateScene) {
        this.fxDecorateScene = fxDecorateScene;
    }

    /**
     * On quit.
     */
    public void onQuit() {
        // for (final ContentController controller : getControllers()) {
        // try {
        // controller.onQuit();
        // } catch (final Exception e) {
        // e.printStackTrace();
        // }
        // }

        this.controllers.forEach(controller -> {
            try {
                onQuit();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        });
    }

}
