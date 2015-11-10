package org.xine.qtime.client.fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

public class WebViewController extends ContentController {
	@FXML
	private WebView webView;
	@FXML
	private BorderPane webViewPane;

	@Override
	public String getName() {
		return "WebView";
	}

	@Override
	public void onActivate() {
		this.webView.getEngine().load("https://www.google.com");
	}

	@Override
	public Node getRootNode() {
		return this.webViewPane;
	}
}
