package org.xine.qtime.client.fx.controllers;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public interface Controller {

	default void onActivate() {
		// Callback - design for extension
	}

	default void onDeactivate() {
		// Callback - design for extension
	}

	default void onQuit() {
		// Callback - design for extension
	}

	public abstract Node getRootNode();

	default void setApplicationController(final ApplicationController applicationController) {
		// default implementation
	}

	public default void setControllerConstrains() {
		if (getRootNode() != null) {
			AnchorPane.setTopAnchor(getRootNode(), Double.valueOf(0d));
			AnchorPane.setLeftAnchor(getRootNode(), Double.valueOf(0d));
			AnchorPane.setRightAnchor(getRootNode(), Double.valueOf(0d));
			AnchorPane.setBottomAnchor(getRootNode(), Double.valueOf(0d));
		}
	}

}
