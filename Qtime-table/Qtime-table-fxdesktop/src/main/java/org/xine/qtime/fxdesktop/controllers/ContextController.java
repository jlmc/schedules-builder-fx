package org.xine.qtime.fxdesktop.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import javax.inject.Inject;

import org.xine.fx.guice.GuiceFXMLLoader;
import org.xine.fx.guice.GuiceFXMLLoader.Result;

/**
 * The Class ContextController.
 */
public abstract class ContextController extends ContentController {

    /** The fxml loader. */
    @Inject
    private GuiceFXMLLoader fxmlLoader;

    /** The controllers. */
    private final List<ContentController> subcontrollers = new ArrayList<>();

    /** The active controller. */
    private ContentController activeController;

    /**
     * Load sub view.
     * @param <T>
     *            the generic type
     * @param subController
     *            the sub controller
     * @return the t
     */
    protected <T> T loadSubView(final String subController) {
        try {
            final Result loadResult = this.fxmlLoader.load(ContextController.class.getResource(subController));
            final T controller = loadResult.getController();
            return controller;
        } catch (final IOException e) {
            throw new RuntimeException("Can't Load the subcontroller " + subController + " caused by: " + e);
        }

    }

    /**
     * Loadsubviews.
     * @param subviews
     *            the subviews
     */
    public void loadsubviews(final Collection<String> subviews) {
        if (subviews != null) {
            for (final String subview : subviews) {
                if (subview != null) {
                    loadSubController(subview);
                }
            }
        }

        if (this.subcontrollers != null && !this.subcontrollers.isEmpty()) {
            activateController(this.subcontrollers.get(0));
        }

    }

    /**
     * Load sub controller.
     * @param subController
     *            the sub controller
     */
    private void loadSubController(final String subController) {
        try {
            final ContentController controller = this.loadSubView(subController);
            this.subcontrollers.add(controller);

            controller.setApplicationController(this.applicationController);

            final Button btn = addControllerButton(controller);
            controller.setNavigationButton(btn);

            if (getComponent() != null) {
                getComponent().getChildren().add(controller.getRootNode());
                controller.setControllerConstrains();
                controller.getRootNode().setVisible(false);
            }

        } catch (final Exception e) {
            System.out.println("SubController OF:" + subController + " - " + e.toString());
            // If any goes wrong, ignore just load we don't want the aplication break because of a internal sub view
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
        // maxWidth="200.0" minWidth="200.0"
        navButton.setPrefWidth(200.0D);
        navButton.setMinWidth(200.0D);
        navButton.setMaxWidth(200.0D);

        navButton.setOnAction(e -> activateController(controller));

        if (getnavItems() != null) {
            getnavItems().getChildren().add(navButton);
        }

        return navButton;
    }

    /**
     * Activate controller.
     * @param controller
     *            the controller
     */
    private void activateController(final ContentController controller) {
        activateController(controller, false);
    }

    /**
     * Activate controller.
     * @param contentController
     *            the content controller
     * @param animate
     *            the animate
     */
    private void activateController(final ContentController contentController, final boolean animate) {
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
    private void animateController(final ContentController contentController, final ContentController oldController, final int direction) {
        // TODO Auto-generated method stub
    }

    /**
     * Gets the component.
     * @return the component
     */
    public abstract Pane getComponent();

    /**
     * Gets the nav items.
     * @return the nav items
     */
    public abstract Pane getnavItems();

}
