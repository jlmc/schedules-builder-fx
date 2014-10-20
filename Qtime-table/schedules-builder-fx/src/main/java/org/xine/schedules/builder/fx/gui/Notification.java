/*
 *
 */
package org.xine.schedules.builder.fx.gui;

import java.util.function.Consumer;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * The Class Notification.
 */
public class Notification {

    /** The Constant VIEW_IMG. */
    private static final String INFO_ICON_FILE = "/images/info.png";

    /** The Constant WARNING_ICON_FILE. */
    private static final String WARNING_ICON_FILE = "/images/warning.png";

    /** The Constant SUCCESS_ICON_FILE. */
    private static final String SUCCESS_ICON_FILE = "/images/success.png";

    /** The Constant ERROR_ICON_FILE. */
    private static final String ERROR_ICON_FILE = "/images/error.png";

    /** The Constant INFO_ICON. */
    public static final Image INFO_ICON = new Image(Notification.class.getResourceAsStream(INFO_ICON_FILE));

    /** The Constant WARNING_ICON. */
    public static final Image WARNING_ICON = new Image(Notification.class.getResourceAsStream(WARNING_ICON_FILE));

    /** The Constant SUCCESS_ICON. */
    public static final Image SUCCESS_ICON = new Image(Notification.class.getResourceAsStream(SUCCESS_ICON_FILE));

    /** The Constant ERROR_ICON. */
    public static final Image ERROR_ICON = new Image(Notification.class.getResourceAsStream(ERROR_ICON_FILE));

    /** The title. */
    private final String title;

    /** The message. */
    private final String message;

    /** The image. */
    private final Image image;

    /**
     * Instantiates a new notification.
     * @param title
     *            the title
     * @param message
     *            the message
     * @param image
     *            the image
     */
    public Notification(final String title, final String message, final Image image) {
        super();
        this.title = title;
        this.message = message;
        this.image = image;
    }

    public Notification(final String title, final String message) {
        this(title, message, SUCCESS_ICON);
    }

    public Notification(final String message, final Image image) {
        this("", message, image);
    }

    /**
     * Gets the title.
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets the image.
     * @return the image
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * Gets the message.
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * The Enum Notifier.
     */
    public enum Notifier {

        /** The instance. */
        INSTANCE;

        /** The Constant ICON_WIDTH. */
        private static final double ICON_WIDTH = 24.0D;

        /** The Constant ICON_HEIGHT. */
        private static final double ICON_HEIGHT = 24.0D;

        /** The width. */
        private static double width = 300.0D;

        /** The height. */
        private static double height = 80.0D;

        /** The offset x. */
        private static double offsetX = 0.0D;

        /** The offset y. */
        private static double offsetY = 25.0D;

        /** The spacing y. */
        private static double spacingY = 5.0D;

        /** The popup location. */
        private static Pos popupLocation = Pos.TOP_RIGHT;

        /** The stage ref. */
        private static Stage stageRef = null;

        /** The popup lifetime. */
        private Duration popupLifeTime;

        /** The stage. */
        private Stage stage;

        /** The scene. */
        private Scene scene;

        /** The popups. */
        private ObservableList<Popup> popups;

        /**
         * Instantiates a new notifier.
         */
        private Notifier() {
            init();
            initGraphics();
        }

        /**
         * Inits the graphics.
         */
        private void initGraphics() {
            this.scene = new Scene(new Region());
            this.scene.setFill(null);
            this.scene.getStylesheets().add(getClass().getResource("/notifier.css").toExternalForm());

            this.stage = new Stage();
            this.stage.initStyle(StageStyle.TRANSPARENT);
            this.stage.setScene(this.scene);

        }

        /**
         * Inits the.
         */
        private void init() {
            this.popupLifeTime = Duration.millis(5000);
            this.popups = FXCollections.observableArrayList();

        }

        /**
         * Sets the popup localization.
         * @param stage
         *            the stage - The Notification will be positioned relative to the given Stage.
         *            If null then the Notification will be positioned relative to the primary Screen.
         * @param popupLocation
         *            the popup location - The default is TOP_RIGHT of primary Screen.
         */
        public static void setPopupLocalization(final Stage stage, final Pos popupLocation) {
            if (null == stage) {
                INSTANCE.stage.initOwner(stage);
                Notifier.stageRef = stage;

            }
            Notifier.popupLocation = popupLocation;
        }

        /**
         * Sets the Notification's owner stage so that when the owner
         * stage is closed Notifications will be shut down as well.<br>
         * This is only needed if <code>setPopupLocation</code> is called
         * <u>without</u> a stage reference.
         * @param owner
         *            the new notification owner
         */
        public static void setNotificationOwner(final Stage owner) {
            INSTANCE.stage.initOwner(owner);
        }

        /**
         * Sets the offset x.
         * @param offsetX
         *            the new offset x
         */
        public static void setOffsetX(final double offsetX) {
            Notifier.offsetX = offsetX;
        }

        /**
         * Sets the offset y.
         * @param offsetY
         *            The vertical shift required. <br>
         *            The default is 25 px.
         */
        public static void setOffsetY(final double offsetY) {
            Notifier.offsetY = offsetY;
        }

        /**
         * Sets the width - The default is 300 px.
         * @param width
         *            the new width
         */
        public static void setWidth(final double width) {
            Notifier.width = width;
        }

        /**
         * Sets the height.
         * @param height
         *            The default is 80 px.
         */
        public static void setHeight(final double height) {
            Notifier.height = height;
        }

        /**
         * Sets the spacing y, The spacing between multiple Notifications.
         * @param spacingY
         *            the new spacing y
         */
        public static void setSpacingY(final double spacingY) {
            Notifier.spacingY = spacingY;
        }

        /**
         * Stop.
         */
        public void stop() {
            this.popups.clear();
            this.stage.close();

        }

        /**
         * Returns the Duration that the notification will stay on screen before it
         * will fade out.
         * @return the Duration the popup notification will stay on screen
         */
        public Duration getPopupLifetime() {
            return this.popupLifeTime;
        }

        /**
         * Sets the popup lifetime.
         * @param popupLifeTime
         *            the new popup lifetime
         */
        public void setPopupLifetime(final Duration popupLifeTime) {
            this.popupLifeTime = Duration.millis(clamp(2000, 20000, popupLifeTime.toMillis()));
        }

        /**
         * Clamp.
         * @param min
         *            the min
         * @param max
         *            the max
         * @param value
         *            the value
         * @return the double
         */
        @SuppressWarnings("static-method")
        private double clamp(final int min, final int max, final double value) {
            if (value < min) {
                return min;
            }
            if (value > max) {
                return max;
            }

            return value;
        }

        /**
         * Show the given Notification on the screen.
         * @param notification
         *            the notification
         */
        public void notify(final Notification notification) {
            preOrder();
            showPopup(notification);
        }

        /**
         * Show popup.
         * @param notification
         *            the notification
         */
        @SuppressWarnings("boxing")
        private void showPopup(final Notification notification) {
            final Label title = new Label(notification.getTitle());
            title.getStyleClass().add("title");

            final ImageView icon = new ImageView(notification.getImage());
            icon.setFitWidth(ICON_WIDTH);
            icon.setFitHeight(ICON_HEIGHT);

            final Label message = new Label(notification.getMessage(), icon);

            message.getStyleClass().add("message");

            final VBox popupLayout = new VBox();
            popupLayout.setSpacing(10);
            popupLayout.setPadding(new Insets(10, 10, 10, 10));
            popupLayout.getChildren().addAll(title, message);

            final StackPane popupContent = new StackPane();
            popupContent.setPrefSize(width, height);
            popupContent.getStyleClass().add("notification");
            popupContent.getChildren().addAll(popupLayout);

            final Popup popup = new Popup();
            popup.setX(getX());
            popup.setY(getY());
            popup.getContent().add(popupContent);

            this.popups.add(popup);

            // Add a timeline for popup fade out
            final KeyValue fadeOutBegin = new KeyValue(popup.opacityProperty(), 1.0);
            final KeyValue fadeOutEnd = new KeyValue(popup.opacityProperty(), 0.0);

            final KeyFrame kfBegin = new KeyFrame(Duration.ZERO, fadeOutBegin);
            final KeyFrame kfEnd = new KeyFrame(Duration.millis(500), fadeOutEnd);

            final Timeline timeline = new Timeline(kfBegin, kfEnd);
            timeline.setDelay(this.popupLifeTime);
            timeline.setOnFinished(actionEvent -> Platform.runLater(() -> {
                popup.hide();
                this.popups.remove(popup);
            }));

            // Move popup to the right during fade out
            // popup.opacityProperty().addListener((observableValue, oldOpacity, opacity) -> popup.setX(popup.getX() + (1.0 - opacity.doubleValue()) * popup.getWidth()));

            if (this.stage.isShowing()) {
                this.stage.toFront();
            } else {
                this.stage.show();
            }

            popup.show(this.stage);
            timeline.play();

        }

        /**
         * Pre order.
         */
        private void preOrder() {
            if (this.popups.isEmpty()) {
                return;
            }

            this.popups.forEach(new Consumer<Popup>() {
                @SuppressWarnings("synthetic-access")
                @Override
                public void accept(final Popup t) {
                    switch (popupLocation) {
                    case TOP_LEFT:
                    case TOP_CENTER:
                    case TOP_RIGHT:
                        t.setY(t.getY() + height + spacingY);
                        break;
                    default:
                        t.setY(t.getY() - height - spacingY);
                    }

                }
            });
        }

        /**
         * Gets the x.
         * @return the x
         */
        public double getX() {
            if (stageRef == null) {
                return calcX(0.0, Screen.getPrimary().getBounds().getWidth());
            }
            return calcX(stageRef.getX(), stageRef.getWidth());
        }

        /**
         * Gets the y.
         * @return the y
         */
        private double getY() {
            if (stageRef == null) {
                return calcY(0.0, Screen.getPrimary().getBounds().getHeight());
            }

            return calcY(stageRef.getY(), stageRef.getHeight());
        }

        /**
         * Calc x.
         * @param left
         *            the left
         * @param totalWidth
         *            the total width
         * @return the double
         */
        @SuppressWarnings("static-method")
        private double calcX(final double left, final double totalWidth) {
            switch (popupLocation) {
            case TOP_LEFT:
            case CENTER_LEFT:
            case BOTTOM_LEFT:
                return left + offsetX;
            case TOP_CENTER:
            case CENTER:
            case BOTTOM_CENTER:
                return left + (totalWidth - width) * 0.5 - offsetX;
            case TOP_RIGHT:
            case CENTER_RIGHT:
            case BOTTOM_RIGHT:
                return left + totalWidth - width - offsetX;
            default:
                return 0.0;
            }
        }

        /**
         * Calc y.
         * @param top
         *            the top
         * @param totalHeight
         *            the total height
         * @return the double
         */
        @SuppressWarnings("static-method")
        private double calcY(final double top, final double totalHeight) {
            switch (popupLocation) {
            case TOP_LEFT:
            case TOP_CENTER:
            case TOP_RIGHT:
                return top + offsetY;
            case CENTER_LEFT:
            case CENTER:
            case CENTER_RIGHT:
                return top + (totalHeight - height) / 2 - offsetY;
            case BOTTOM_LEFT:
            case BOTTOM_CENTER:
            case BOTTOM_RIGHT:
                return top + totalHeight - height - offsetY;
            default:
                return 0.0;
            }
        }

        /**
         * Notify. Show a Notification with the given parameters on the screen.
         * @param title
         *            the title
         * @param message
         *            the message
         * @param image
         *            the image
         */
        public void notify(final String title, final String message, final Image image) {
            notify(new Notification(title, message, image));
        }

        /**
         * Show a Notification with the given title and message and an Info icon.
         * @param title
         *            the title
         * @param message
         *            the message
         */
        public void notifyInfo(final String title, final String message) {
            notify(new Notification(title, message, Notification.INFO_ICON));
        }
    }

}
