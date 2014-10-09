package org.xine.schedules.builder.fx.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import javax.inject.Inject;

import org.xine.fx.guice.GuiceFXMLLoader;
import org.xine.fx.guice.GuiceFXMLLoader.Result;

/**
 * The Class CompositeContentController.
 */
public abstract class CompositeContentController extends ContentController {

    /** The fxml loader. */
    @Inject
    private GuiceFXMLLoader fxmlLoader;

    /** The sub views. */
    private Set<String> subViews;

    /** The controllers. */
    private final List<ContentController> controllers = new ArrayList<>();

    /** The active controller. */
    private ContentController activeController;

    /** The nav buttons. */
    private final List<Button> navButtons = new ArrayList<>();

    /**
     * Gets the nav buttons.
     * @return the nav buttons
     */
    public List<Button> getNavButtons() {
        return this.navButtons;
    }

    /**
     * Gets the content.
     * @return the content
     */
    protected abstract Pane getContent();

    /**
     * Gets the navigation content.
     * @return the navigation content
     */
    protected abstract Pane getNavigationContent();

    /**
     * Sets the nav button properties.
     * @param navButton
     *            the new nav button properties
     */
    protected abstract void setNavButtonProperties(Button navButton);

    /**
     * Sets the sub views.
     * @param subViews
     *            the new sub views
     */
    protected void setSubViews(final String[] subViews) {

        if (subViews != null) {
            this.subViews = new LinkedHashSet<>(Arrays.asList(subViews));
        } else {
            this.subViews = new LinkedHashSet<>();
        }
    }

    /**
     * Gets the fxml loader.
     * @return the fxml loader
     */
    protected GuiceFXMLLoader getFxmlLoader() {
        return this.fxmlLoader;
    }

    /**
     * Load sub controllers.
     */
    protected void loadSubControllers() {

        // this.subViews.forEach((final String subview) -> loadSubControllers(subview));

        for (final String subview : this.subViews) {
            loadSubControllers(subview);
        }

        if (this.controllers != null && !this.controllers.isEmpty()) {
            this.activateController(this.controllers.get(0), false);
        }
    }

    /**
     * Load sub controllers.
     * @param subController
     *            the sub controller
     */
    private void loadSubControllers(final String subController) {

        try {
            final Result loadResult = this.fxmlLoader.load(CompositeContentController.class.getResource(subController));
            final ContentController controller = loadResult.getController();

            this.controllers.add(controller);

            controller.setApplicationController(getApplicationController());

            //
            addControllerButton(controller);
            //

            getContent().getChildren().add(controller.getRootNode());
            setControllerConstrains(controller);

            controller.getRootNode().setVisible(false);

        } catch (final IOException e) {
            throw new RuntimeException("Could not load the Sub View: " + subController);
        }

    }

    /**
     * Adds the controller button.
     * @param controller
     *            the controller
     */
    private void addControllerButton(final ContentController controller) {

        final Button navButton = new Button(controller.getName());
        setNavButtonProperties(navButton);
        controller.setNavigationButton(navButton);

        // navButton.setMinWidth(getNavigationContent().widthProperty().doubleValue());
        navButton.setPrefWidth(150.0);
        // navButton.setPrefHeight(39);

        navButton.setOnAction(new EventHandler<ActionEvent>() {
            @SuppressWarnings("synthetic-access")
            @Override
            public void handle(final ActionEvent arg0) {
                activateController(controller);
            }
        });

        if (this.navButtons.isEmpty()) {
            navButton.getStyleClass().add("first");
            navButton.getStyleClass().add("last");
            this.navButtons.add(navButton);
        } else {
            navButton.getStyleClass().add("last");
            this.navButtons.get(this.navButtons.size() - 1).getStyleClass().removeAll("last");
            navButton.getStyleClass().add("last");
            this.navButtons.add(navButton);
        }

        getNavigationContent().getChildren().add(navButton);

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
     * @param activeController2
     *            the active controller2
     * @param oldActiveController
     *            the old active controller
     * @param direction
     *            the direction
     */
    protected void animateController(final ContentController activeController2, final ContentController oldActiveController, final int direction) {
        // TODO
    }

    @Override
    public void onQuit() {

        for (final ContentController c : this.controllers) {
            c.onQuit();
        }
    }
}
