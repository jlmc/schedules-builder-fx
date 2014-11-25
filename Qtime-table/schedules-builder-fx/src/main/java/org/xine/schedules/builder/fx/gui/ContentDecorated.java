package org.xine.schedules.builder.fx.gui;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import org.xine.schedules.builder.fx.backoffice.Status;

/**
 * The Interface ContentDecorated.
 */
public interface ContentDecorated {

    /**
     * Gets the name.
     * @return the name
     */
    public String getName();

    /**
     * Sets the name.
     * @param name
     *            the new name
     */
    public void setName(final String name);

    /**
     * Gets the navigation button.
     * @return the navigation button
     */
    public Button getNavigationButton();

    /**
     * Sets the navigation button.
     * @param activationButton
     *            the new navigation button
     */
    void setNavigationButton(final Button activationButton);

    /**
     * Gets the root node.
     * @return the root node
     */
    public Node getRootNode();

    /**
     * Sets the application controller.
     * @param applicationController
     *            the new application controller
     */
    public void setApplicationController(final ApplicationController applicationController);

    /**
     * On activate.
     */
    public default void onActivate() {
        // Callback - design for extension
    }

    /**
     * On deactivate.
     */
    public default void onDeactivate() {
        // Callback - design for extension
    }

    /**
     * On quit.
     */
    public default void onQuit() {
        // Callback - design for extension
    }

    /**
     * Change status.
     * @param status
     *            the status
     */
    public default void changeStatus(final Status status) {
        // Callback - design for extension
    }

    /**
     * Sets the controller constrains.
     * @param controller
     *            the new controller constrains
     */
    public default void setControllerConstrains(final ContentController controller) {
        AnchorPane.setTopAnchor(controller.getRootNode(), Double.valueOf(0d));
        AnchorPane.setLeftAnchor(controller.getRootNode(), Double.valueOf(0d));
        AnchorPane.setRightAnchor(controller.getRootNode(), Double.valueOf(0d));
        AnchorPane.setBottomAnchor(controller.getRootNode(), Double.valueOf(0d));
    }

}
