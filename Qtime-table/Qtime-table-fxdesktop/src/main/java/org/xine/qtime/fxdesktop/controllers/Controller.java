package org.xine.qtime.fxdesktop.controllers;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * The Interface ActivatableController.
 */
public interface Controller {

    /** The application controller. */
    ApplicationController applicationController = null;

    /**
     * On activate.
     */
    default public void onActivate() {
        // Callback - design for extension
    }

    /**
     * On deactivate.
     */
    default public void onDeactivate() {
        // Callback - design for extension
    }

    /**
     * On quit.
     */
    default public void onQuit() {
        // Callback - design for extension
    }

    /**
     * Gets the root node.
     * @return the root node
     */
    public abstract Node getRootNode();

    /**
     * Sets the application controller.
     * @param applicationController
     *            the new application controller
     */
    default public void setApplicationController(final ApplicationController applicationController) {
        // default implementation
    }

    /**
     * Sets the controller constrains.
     * @param controller
     *            the new controller constrains
     */
    public default void setControllerConstrains() {
        if (getRootNode() != null) {
            AnchorPane.setTopAnchor(getRootNode(), Double.valueOf(0d));
            AnchorPane.setLeftAnchor(getRootNode(), Double.valueOf(0d));
            AnchorPane.setRightAnchor(getRootNode(), Double.valueOf(0d));
            AnchorPane.setBottomAnchor(getRootNode(), Double.valueOf(0d));
        }
    }

}
