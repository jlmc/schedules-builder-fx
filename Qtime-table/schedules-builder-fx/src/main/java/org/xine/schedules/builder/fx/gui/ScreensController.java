package org.xine.schedules.builder.fx.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

// TODO: Auto-generated Javadoc
/**
 * The Class ScreensController.
 */
public class ScreensController extends StackPane {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(ScreensController.class.getName());

    /** The screens. */
    private final HashMap<String, Node> screens = new HashMap<>();

    /** The controllers. */
    private final HashMap<String, ControlledScreen> controllers = new HashMap<>();

    /**
     * Instantiates a new screens controller.
     */
    public ScreensController() {
        super();
    }

    /**
     * Adds the screen.
     * @param name
     *            the name
     * @param screen
     *            the screen
     */
    public void addScreen(final String name, final Node screen) {
        if (name != null && screen != null) {
            this.screens.put(name, screen);
        }
    }

    /**
     * Adds the controller.
     * @param name
     *            the name
     * @param controlledScreen
     *            the controlled screen
     */
    public void addController(final String name, final ControlledScreen controlledScreen) {
        if (name != null && controlledScreen != null) {
            this.controllers.put(name, controlledScreen);
        }
    }

    /**
     * Gets the screen.
     * @param name
     *            the name
     * @return the screen
     */
    public Node getScreen(final String name) {
        return this.screens.get(name);
    }

    /**
     * Load screen.
     * @param name
     *            the name
     * @param resource
     *            the resource
     * @return true, if successful
     */
    public boolean loadScreen(final String name, final String resource) {

        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));
            final Parent loadScreen = (Parent) fxmlLoader.load();
            final ControlledScreen controlledScreen = ((ControlledScreen) fxmlLoader.getController());
            controlledScreen.setScreenParent(this);

            addScreen(name, loadScreen);
            addController(name, controlledScreen);

            return true;
        } catch (final IOException e) {
            // System.out.println(e.getMessage());
            throw new RuntimeException("Can't load the resource " + resource);
        }
    }

    /**
     * This method tries to displayed the screen with a predefined name.
     * First it makes sure the screen has been already loaded. Then if there is more than
     * one screen the new screen is been added second, and then the current screen is removed.
     * If there isn't any screen being displayed, the new screen is just added to the root.
     * @param name
     *            the name
     * @return true, if successful
     */
    public boolean setScreen(final String name) {
        if (this.screens.get(name) != null) {
            final DoubleProperty opacity = opacityProperty();

            // if there is more than one screen
            if (!getChildren().isEmpty()) {
                final Timeline fade = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, Double.valueOf(1.0D))), new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>() {
                    @SuppressWarnings("synthetic-access")
                    @Override
                    public void handle(final ActionEvent t) {
                        // remove the displayed screen
                        getChildren().remove(0);
                        getChildren().add(0, ScreensController.this.screens.get(name)); // add the screen
                        final Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, Double.valueOf(0.0d))), new KeyFrame(new Duration(800), new KeyValue(opacity, Double.valueOf(1.0D))));
                        fadeIn.play();
                    }
                }, new KeyValue(opacity, Double.valueOf(0.0D))));
                fade.play();

            } else {
                setOpacity(0.0);
                // no one else been displayed, then just show
                getChildren().add(this.screens.get(name));
                final Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, Double.valueOf(0.0D))), new KeyFrame(new Duration(2500), new KeyValue(opacity, Double.valueOf(1.0D))));
                fadeIn.play();
            }
            return true;
        }
        LOGGER.info("screen hasn't been loaded!!!");
        return false;
    }

    /**
     * remove the screen with the given name from the collection of screens.
     * @param name
     *            the name
     * @return true, if the screen has been removed and is not null, false otherwise
     */
    public boolean unloadScreen(final String name) {
        if (this.screens.remove(name) == null) {
            return false;
        }
        return true;
    }

}
