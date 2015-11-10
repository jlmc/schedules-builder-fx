package org.xine.qtime.client.fx.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;

public abstract class ContentController implements Controller {

	private final StringProperty name = new SimpleStringProperty("<name>");

	private Button navigationButton = null;

	private ApplicationController applicationController = null;

	public ApplicationController getApplicationController() {
		return this.applicationController;
	}

	@Override
	public void onActivate() {
		// Callback - design for extension
	}

	@Override
	public void onDeactivate() {
		// Callback - design for extension
	}

	@Override
	public void onQuit() {
		// Callback - design for extension
	}

	public String getName() {
		return this.name.get();
	}

	public void setName(final String name) {
		this.name.set(name);
	}

	public StringProperty nameProperty() {
		return this.name;
	}

	@Override
	public abstract Node getRootNode();

	@Override
	public void setApplicationController(final ApplicationController applicationController) {
		if (this.applicationController != null) {
			throw new IllegalStateException("application controller was already set");
		}
		this.applicationController = applicationController;
	}

	/**
	 * Sets the navigation button.
	 * 
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
	 * 
	 * @return the navigation button
	 */
	public Button getNavigationButton() {
		return this.navigationButton;
	}
}
