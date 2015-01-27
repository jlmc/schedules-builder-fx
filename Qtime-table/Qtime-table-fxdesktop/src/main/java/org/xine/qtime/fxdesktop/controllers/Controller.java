/* 
* Copyright (c) 2015 Qxine <https://github.com/jlmc>
* All Rights Reserved, unless otherwise granted permission.
*
* You may use and modify for private use, fork the official repository
* for contribution purposes, contribute code, and reuse your own code.
*/
package org.xine.qtime.fxdesktop.controllers;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * The Interface ActivatableController.
 */
public interface Controller {

    /**
     * On activate.
     */
    default void onActivate() {
        // Callback - design for extension
    }

    /**
     * On deactivate.
     */
    default void onDeactivate() {
        // Callback - design for extension
    }

    /**
     * On quit.
     */
    default void onQuit() {
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
    default void setApplicationController(final ApplicationController applicationController) {
        // default implementation
    }

    /**
     * Sets the controller constrains.
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
