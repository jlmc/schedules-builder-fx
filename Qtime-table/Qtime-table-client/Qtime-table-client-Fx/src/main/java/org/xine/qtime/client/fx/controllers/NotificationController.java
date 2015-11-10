package org.xine.qtime.client.fx.controllers;

import javax.management.Notification;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.WritableValue;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NotificationController {

	private Stage stage;

	@FXML
	private Rectangle arrow;

	@FXML
	private Rectangle titleBar;

	@FXML
	private ListView<Notification> notificationList;

	@FXML
	private AnchorPane content;

	private boolean animating = false;

	private WritableValue<Double> stageHeight;

	public void init() {
		this.stage.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			if (!newValue) {
				Platform.runLater(() -> {
					if (!this.animating) {
						hide();
					}
				});
			}

		});

		getStage().getScene().setFill(null);

		this.stageHeight = new WritableValue<Double>() {
			@Override
			public void setValue(final Double value) {
				getStage().setHeight(value);
			}

			@Override
			public Double getValue() {
				return getStage().getHeight();
			}
		};

		this.arrow.setPickOnBounds(true);
	}

	public Stage getStage() {
		return this.stage;
	}

	public void setStage(final Stage stage) {
		this.stage = stage;
	}

	public Point2D getArrowPosition() {
		// final Bounds b =
		// this.arrow.localToScene(this.arrow.getLayoutBounds());
		final Bounds b = this.arrow.localToParent(this.arrow.getLayoutBounds());

		final double y = -1 * this.arrow.getHeight();
		final double x = (b.getMinX() + b.getMaxX() + this.arrow.getWidth()) / 2d;
		// final double y = b.getMinY();
		// final double x = (b.getMinX() + b.getMaxX()) / 2d;
		return new Point2D(x, y);
	}

	public void show() {
		if (this.stage.isShowing() || this.animating) {
			this.stage.show();
			return;
		}
		this.animating = true;
		final double startHeight = this.titleBar.localToScene(this.titleBar.layoutBoundsProperty().get()).getMaxY();
		final double finalHeight = 350;

		final Timeline timeline = new Timeline();
		KeyValue titleOpacity = new KeyValue(this.stage.opacityProperty(), 0.0);
		KeyValue height = new KeyValue(this.stageHeight, startHeight);
		final KeyFrame zero = new KeyFrame(Duration.ZERO, titleOpacity, height);
		titleOpacity = new KeyValue(this.stage.opacityProperty(), 1.0);
		height = new KeyValue(this.stageHeight, startHeight);
		final KeyFrame fadeIn = new KeyFrame(Duration.millis(200), titleOpacity, height);
		height = new KeyValue(this.stageHeight, finalHeight);
		final KeyFrame slide = new KeyFrame(Duration.millis(400), height);
		timeline.getKeyFrames().addAll(zero, fadeIn, slide);
		timeline.play();
		this.stage.show();
		timeline.setOnFinished(event -> setAnimating(false));
	}

	public void hide() {
		System.out.println("Hiding..." + this.animating);
		if (!this.stage.isShowing() || this.animating) {
			this.stage.hide();
			return;
		}
		this.animating = true;

		this.stage.hide();
		this.stage.show();

		final double startHeight = this.titleBar.localToScene(this.titleBar.layoutBoundsProperty().get()).getMaxY();
		final double finalHeight = this.stage.getHeight();

		final Timeline timeline = new Timeline();
		final KeyValue height = new KeyValue(this.stageHeight, startHeight);
		final KeyFrame slide = new KeyFrame(Duration.millis(400), height);
		final KeyValue titleOpacity = new KeyValue(this.stage.opacityProperty(), 0.0);
		final KeyFrame fadeOut = new KeyFrame(Duration.millis(200), titleOpacity);
		timeline.getKeyFrames().addAll(slide, fadeOut);
		timeline.play();
		timeline.setOnFinished(event -> {
			NotificationController.this.stage.hide();
			NotificationController.this.stage.setHeight(finalHeight);
			setAnimating(false);
		});
	}

	private void setAnimating(final boolean animating) {
		this.animating = animating;
	}

	public boolean isAnimating() {
		return this.animating;
	}
}
