package org.xine.qtime.fxdesktop.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private final List<Controller> controllers = new ArrayList<>();

    /** The active controller. */
    private Controller activeController;

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
     * Gets the controllers.
     * @return the controllers
     */
    public List<Controller> getControllers() {
        return this.controllers;
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
     * @param controller
     *            the controller
     * @param anime
     *            the anime
     */
    private void activateController(final ContentController controller, final boolean anime) {
        if (this.activeController == controller) {
            return;
        }
        final int from = this.controllers.indexOf(this.activeController);
        final int to = this.controllers.indexOf(controller);
        final Controller oldController = this.activeController;
        controller.getRootNode().setVisible(true);
        if (this.activeController != null) {
            this.activeController.getRootNode().setVisible(false);
            this.activeController.onDeactivate();
            removeStyleClassSelected(oldController);
            // /this.activeController.getNavigationButton().getStyleClass().remove("selected");
        }
        this.activeController = controller;
        addStyleClassSelected(controller);
        // this.activeController.getNavigationButton().getStyleClass().add("selected");

        // final int direction = from < to ? -1 : 1;
        // if (animate && oldController != null) {
        // animateController(controller, oldController, direction);
        // }
        this.activeController.onActivate();

    }

    /**
     * Adds the style class selected.
     * @param controller
     *            the controller
     */
    private void addStyleClassSelected(final Controller controller) {
        if (controller instanceof ContextController) {
            if (((ContentController) controller).getNavigationButton() != null) {
                ((ContentController) controller).getNavigationButton().getStyleClass().add("selected");
            }
        }
    }

    /**
     * Removes the style class selected.
     * @param controller
     *            the controller
     */
    private void removeStyleClassSelected(final Controller controller) {
        if (controller instanceof ContextController) {
            if (((ContentController) controller).getNavigationButton() != null) {

                ((ContentController) controller).getNavigationButton().getStyleClass().remove("selected");
            }

        }

    }
}
