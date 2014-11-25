package org.xine.schedules.builder.fx.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * The Class ContentController.
 */
public abstract class ContentController {

    /** The name. */
    private final StringProperty name = new SimpleStringProperty("<name>");

    /** The application controller. */
    private ApplicationController applicationController = null;

    /** The navigation button. */
    private Button navigationButton = null;

    /**
     * Gets the application controller.
     * @return the application controller
     */
    protected ApplicationController getApplicationController() {
        return this.applicationController;
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
        return this.name.get();
    }

    /**
     * Gets the navigation button.
     * @return the navigation button
     */
    public Button getNavigationButton() {
        return this.navigationButton;
    }

    /**
     * Gets the root node.
     * @return the root node
     */
    public abstract Node getRootNode();

    /**
     * Name property.
     * @return the string property
     */
    public StringProperty nameProperty() {
        return this.name;
    }

    /**
     * On activate.
     */
    public void onActivate() {
        // Callback - design for extension
    }

    /**
     * On deactivate.
     */
    public void onDeactivate() {
        // Callback - design for extension
    }

    /**
     * On quit.
     */
    public void onQuit() {
        // Callback - design for extension
    }

    /**
     * Sets the application controller.
     * @param applicationController
     *            the new application controller
     */
    public final void setApplicationController(final ApplicationController applicationController) {
        if (this.applicationController != null) {
            throw new IllegalStateException("application controller was already set");
        }
        this.applicationController = applicationController;
    }

    /**
     * Sets the name.
     * @param name
     *            the new name
     */
    public void setName(final String name) {
        this.name.set(name);
    }

    /**
     * Sets the navigation button.
     * @param activationButton
     *            the new navigation button
     */
    public final void setNavigationButton(final Button activationButton) {
        if (this.navigationButton != null) {
            throw new IllegalStateException("navigation button was already set");
        }
        this.navigationButton = activationButton;
    }

    /**
     * Sets the controller constrains.
     */
    public void setControllerConstrains() {
        setControllerConstrains(this);
    }

    /**
     * Sets the controller constrains.
     * @param controller
     *            the new controller constrains
     */
    public static void setControllerConstrains(final ContentController controller) {
        AnchorPane.setTopAnchor(controller.getRootNode(), Double.valueOf(0d));
        AnchorPane.setLeftAnchor(controller.getRootNode(), Double.valueOf(0d));
        AnchorPane.setRightAnchor(controller.getRootNode(), Double.valueOf(0d));
        AnchorPane.setBottomAnchor(controller.getRootNode(), Double.valueOf(0d));
    }
}
