package org.xine.schedules.builder.fx.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import javax.inject.Inject;

import org.xine.fx.guice.GuiceFXMLLoader;
import org.xine.fx.guice.GuiceFXMLLoader.Result;

// TODO: Auto-generated Javadoc
/**
 * The Class CompositeContentController.
 */
public abstract class CompositeContentController extends ContentController {

    /** The fxml loader. */
    @Inject
    private GuiceFXMLLoader fxmlLoader;

    /** The sub views. */
    private Set<String> subViews;

    /** The controllers. */
    private final List<ContentController> controllers = new ArrayList<ContentController>();

    /** The active controller. */
    private ContentController activeController;

    /** The nav buttons. */
    public List<Button> navButtons = new ArrayList<>();

    // public Node getRootNode() {
    // return null;
    // // TODO Auto-generated method stub
    //
    // }

    /**
     * Gets the content.
     * @return the content
     */
    protected abstract Pane getContent();

    /**
     * Gets the navigation content.
     * @return the navigation content
     */
    protected abstract Pane getNavigationContent();

    /**
     * Sets the nav button properties.
     * @param navButton
     *            the new nav button properties
     */
    protected abstract void setNavButtonProperties(Button navButton);

    /**
     * Sets the sub views.
     * @param subViews
     *            the new sub views
     */
    protected void setSubViews(final String[] subViews) {

        if (subViews != null) {
            this.subViews = new LinkedHashSet<>(Arrays.asList(subViews));
        } else {
            this.subViews = new LinkedHashSet<>();
        }
    }

    /**
     * Gets the fxml loader.
     * @return the fxml loader
     */
    protected GuiceFXMLLoader getFxmlLoader() {
        return this.fxmlLoader;
    }

    /**
     * Load sub controllers.
     */
    protected void loadSubControllers() {

        //this.subViews.forEach((final String subview) -> loadSubControllers(subview));

         for (final String subview : this.subViews) {
        	 loadSubControllers(subview);
         }

        if (this.controllers != null && !this.controllers.isEmpty()) {
            this.activateController(this.controllers.get(0), false);
        }
    }

    /**
     * Load sub controllers.
     * @param subController
     *            the sub controller
     */
    private void loadSubControllers(final String subController) {

        try {
            final Result loadResult = this.fxmlLoader.load(CompositeContentController.class.getResource(subController));
            final ContentController controller = loadResult.getController();

            this.controllers.add(controller);

            controller.setApplicationController(getApplicationController());

            //
            addControllerButton(controller);
            //

            getContent().getChildren().add(controller.getRootNode());
            setControllerConstrains(controller);

            controller.getRootNode().setVisible(false);

        } catch (final IOException e) {
            throw new RuntimeException("Could not load the Sub View: " + subController);
        }

    }

    /**
     * Adds the controller button.
     * @param controller
     *            the controller
     */
    private void addControllerButton(final ContentController controller) {

        final Button navButton = new Button(controller.getName());
        setNavButtonProperties(navButton);
        controller.setNavigationButton(navButton);

        // navButton.setMinWidth(getNavigationContent().widthProperty().doubleValue());
        navButton.setPrefWidth(150.0);
        //navButton.setPrefHeight(39);
        

        navButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				activateController(controller);
			}
		});
        
        
        if(navButtons.isEmpty()){
        	navButton.getStyleClass().add("first");
        	navButton.getStyleClass().add("last");
        	navButtons.add(navButton);
        }
        else{
        	navButton.getStyleClass().add("last");
        	navButtons.get(navButtons.size() -1).getStyleClass().removeAll("last");
        	navButton.getStyleClass().add("last");
        	navButtons.add(navButton);
        }
        
        getNavigationContent().getChildren().add(navButton);

    }

    /**
     * Activate controller.
     * @param contentController
     *            the content controller
     */
    private void activateController(final ContentController contentController) {
        activateController(contentController, true);
    }

    /**
     * Activate controller.
     * @param contentController
     *            the content controller
     * @param animate
     *            the animate
     */
    private void activateController(final ContentController contentController, final boolean animate) {
        if (contentController == this.activeController) {
            return;
        }

        final int from = this.controllers.indexOf(this.activeController);
        final int to = this.controllers.indexOf(contentController);

        final ContentController oldActiveController = this.activeController;
        contentController.getRootNode().setVisible(true);

        if (oldActiveController != null) {
            oldActiveController.onDeactivate();
            oldActiveController.getRootNode().setVisible(false);
            oldActiveController.getNavigationButton().getStyleClass().remove("selected");
        }

        this.activeController = contentController;
        this.activeController.getNavigationButton().getStyleClass().add("selected");

        if (animate && oldActiveController != null) {
            final int direction = from < to ? -1 : 1;
            animateController(this.activeController, oldActiveController, direction);
        }
       
        	 
       

        this.activeController.onActivate();
    }

    /**
     * Animate controller.
     * @param activeController2
     *            the active controller2
     * @param oldActiveController
     *            the old active controller
     * @param direction
     *            the direction
     */
    protected void animateController(final ContentController activeController2, final ContentController oldActiveController, final int direction) {
       

    }
    
    /*
    public boolean setScreen(final String name) {
        if (this.screens.get(name) != null) {
            final DoubleProperty opacity = opacityProperty();

            // final ControlledScreen controlledScreen = this.controllers.get(name);
            // if (controlledScreen != null) {
            // controlledScreen.enable();
            // }

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
*/

}
