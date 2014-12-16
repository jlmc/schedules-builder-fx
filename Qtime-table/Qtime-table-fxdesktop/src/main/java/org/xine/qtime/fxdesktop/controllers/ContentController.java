package org.xine.qtime.fxdesktop.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * The Class ContentController.
 */
public abstract class ContentController implements Controller {

    /** The name. */
    private final StringProperty name = new SimpleStringProperty("<name>");

    /** The navigation button. */
    protected Button navigationButton = null;

    /** The application controller. */
    protected ApplicationController applicationController = null;

    /**
     * On activate.
     */
    @Override
    public void onActivate() {
        // Callback - design for extension
    }

    /**
     * On deactivate.
     */
    @Override
    public void onDeactivate() {
        // Callback - design for extension
    }

    /**
     * On quit.
     */
    @Override
    public void onQuit() {
        // Callback - design for extension
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
        return this.name.get();
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
     * Name property.
     * @return the string property
     */
    public StringProperty nameProperty() {
        return this.name;
    }

    /**
     * Gets the root node.
     * @return the root node
     */
    @Override
    public abstract Node getRootNode();

    /**
     * Sets the application controller.
     * @param applicationController
     *            the new application controller
     */
    @Override
    public final void setApplicationController(final ApplicationController applicationController) {
        if (this.applicationController != null) {
            throw new IllegalStateException("application controller was already set");
        }
        this.applicationController = applicationController;
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
     * Gets the navigation button.
     * @return the navigation button
     */
    public Button getNavigationButton() {
        return this.navigationButton;
    }
}