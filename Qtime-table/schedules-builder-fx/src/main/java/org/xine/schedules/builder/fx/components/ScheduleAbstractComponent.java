package org.xine.schedules.builder.fx.components;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javax.inject.Inject;

import org.xine.fx.guice.FXMLComponent;
import org.xine.fx.guice.GuiceFXMLLoader;
import org.xine.fx.guice.GuiceFXMLLoader.Result;
import org.xine.schedules.builder.fx.gui.ApplicationController;
import org.xine.schedules.builder.fx.gui.ContentController;
import org.xine.schedules.builder.fx.gui.UiUtils;

/**
 * The Class ScheduleAbstractComponent.
 */
@FXMLComponent(resources = "org.xine.schedules.builder.fx.components.subjects.SubjectsPane")
public abstract class ScheduleAbstractComponent extends AnchorPane {

    /** The application controller. */
    private ApplicationController applicationController;

    /** The fxml loader. */
    @Inject
    private GuiceFXMLLoader fxmlLoader;

    /** The subcontrollers. */
    private final Map<SubType, ScheduleAbstractContentController> subcontrollers = new HashMap<>();

    /** The active controller. */
    private ScheduleAbstractContentController activeController;

    /**
     * Gets the content.
     * @return the content
     */
    protected abstract Pane getContent();

    /**
     * Load sub controllers.
     * @param views
     *            the views
     */
    protected void loadSubControllers(final Map<SubType, String> views) {
        if (views != null && !views.isEmpty()) {

            for (final SubType t : views.keySet()) {
                if (views.get(t) != null) {

                    final ScheduleAbstractContentController controller = getSubController(views.get(t));
                    controller.setApplicationController(this.applicationController);
                    controller.setParentComponent(this);
                    getContent().getChildren().add(controller.getRootNode());
                    UiUtils.setControllerConstrains(controller);

                    controller.getRootNode().setVisible(false);
                    this.subcontrollers.put(t, controller);
                }
            }
        }
    }

    /**
     * Gets the sub controller.
     * @param <N>
     *            the number type
     * @param view
     *            the view
     * @return the sub controller
     */
    @SuppressWarnings("unchecked")
    private <N> N getSubController(final String view) {
        try {
            final Result loadResult = this.fxmlLoader.load(ApplicationController.class.getResource(view));
            final ContentController controller = loadResult.getController();
            return (N) controller;
        } catch (final Exception e) {
            throw new RuntimeException("Could not load sub view: " + view, e);
        }

    }

    /**
     * Activate controller.
     * @param subtype
     *            the subtype
     */
    public void activateController(final SubType subtype) {
        if (subtype != null) {
            this.activateController(this.subcontrollers.get(subtype), false);
        }
    }

    // private void activateController(final ScheduleAbstractContentController contentController) {
    // activateController(contentController, true);
    //
    // }

    /**
     * Activate controller.
     * @param contentController
     *            the content controller
     * @param b
     *            the b
     */
    private void activateController(final ScheduleAbstractContentController contentController, final boolean b) {

        if (contentController == null && contentController == this.activeController) {
            return;
        }

        final ContentController oldActiveController = this.activeController;
        contentController.getRootNode().setVisible(true);

        if (oldActiveController != null) {
            oldActiveController.onDeactivate();
            oldActiveController.getRootNode().setVisible(false);
        }

        this.activeController = contentController;
        this.activeController.onActivate();

    }

    /**
     * The application controller.
     * @param c
     *            the new application controller
     */
    public void setApplicationController(final ApplicationController c) {
        this.applicationController = c;
    }

    /**
     * On activate.
     * Callback - design for extension
     */
    public void onActivate() {
        //
    }

    /**
     * On deactivate.
     * Callback - design for extension
     */
    public void onDeactivate() {
        //
    }

    /**
     * On quit.
     * Callback - design for extension
     */
    public void onQuit() {
        //
    }
}
