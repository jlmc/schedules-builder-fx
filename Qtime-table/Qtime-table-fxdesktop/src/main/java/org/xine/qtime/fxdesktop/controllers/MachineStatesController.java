package org.xine.qtime.fxdesktop.controllers;

import org.xine.fx.guice.GuiceFXMLLoader;
import org.xine.fx.guice.GuiceFXMLLoader.Result;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import javax.inject.Inject;

/**
 * The Class MachineStatesController.
 */
public abstract class MachineStatesController extends ContentController {

    /**
     * The Enum States.
     */
    public enum State {

        /** The edit. */
        EDIT,
        /** The create. */
        CREATE,
        /** The search. */
        SEARCH,
        /** The list. */
        LIST
    }

    /** The fxml loader. */
    @Inject
    private GuiceFXMLLoader fxmlLoader;

    /** The states. */
    private final Map<State, StateController> states = new HashMap<>();

    /** The active controller. */
    private StateController activeController;

    /** The state state. */
    private final State stateState;

    /**
     * Instantiates a new machine states controller.
     */
    public MachineStatesController() {
        super();
        this.stateState = State.LIST;
    }

    /**
     * Instantiates a new machine states controller.
     * @param stateState
     *            the state state
     */
    public MachineStatesController(final State stateState) {
        super();
        this.stateState = stateState;
    }

    /**
     * Inits the states.
     * Called to initialized all state controllers defined on the Map views parameters,
     * @param views
     *            the views map, with the path to the views
     * @param location
     *            The location used to resolve relative paths for the root object,
     *            or <tt>null</tt> if the location is not known.
     * @param resources
     *            The resources used to localize the root object, or <tt>null</tt> if
     *            the root object was not localized.
     */
    public void loadStates(final Map<State, String> views, final URL location,
            final ResourceBundle resources) {
        if (views != null && !views.isEmpty()) {
            loadsubviews(views, location, resources);
        }
    }

    /**
     * Loadsubviews.
     * @param subviews
     *            the subviews
     * @param location
     *            the location
     * @param resources
     *            the resources
     */
    private void loadsubviews(final Map<State, String> subviews, final URL location,
            final ResourceBundle resources) {

        for (final State state : subviews.keySet()) {
            if (subviews.get(state) != null) {
                loadSubController(state, subviews.get(state));
            }
        }
        if (this.states != null && !this.states.isEmpty() && this.stateState != null) {
            activateController(this.states.get(this.stateState));
        }
        // if (this.subcontrollers != null && !this.subcontrollers.isEmpty()) {
        // activateController(this.subcontrollers.get(0));
        // }

    }

    /**
     * Activate controller.
     * @param contentController
     *            the content controller
     */
    private void activateController(final StateController contentController) {
        if (this.activeController == contentController
                && !this.states.values().contains(contentController)) {
            return;
        }
        // final int from = this.controllers.indexOf(this.activeController);
        // final int to = this.controllers.indexOf(contentController);
        // final ContentController oldController = this.activeController;
        contentController.getRootNode().setVisible(true);
        if (this.activeController != null) {
            this.activeController.getRootNode().setVisible(false);
            this.activeController.onDeactivate();

            // this.activeController.getNavigationButton().getStyleClass().remove("selected");
        }
        this.activeController = contentController;
        // this.activeController.getNavigationButton().getStyleClass().add("selected");
        // final int direction = from < to ? -1 : 1;
        // if (animate && oldController != null) {
        // animateController(contentController, oldController, direction);
        // }
        this.activeController.onActivate();
    }

    /**
     * Load sub controller.
     * @param state
     *            the state
     * @param subController
     *            the sub controller
     * @return the content controller
     */
    private ContentController loadSubController(final State state, final String subController) {
        try {
            final StateController controller = this.loadSubView(subController);

            // this.subcontrollers.add(controller);
            controller.setMachineStatesController(this);

            this.states.put(state, controller);

            controller.setApplicationController(this.applicationController);

            // final Button btn = addControllerButton(controller);
            // controller.setNavigationButton(btn);

            if (getContent() != null) {
                getContent().getChildren().add(controller.getRootNode());
                controller.setControllerConstrains();
                controller.getRootNode().setVisible(false);
            }

            return controller;

        } catch (final Exception e) {
            System.out.println("SubController OF:" + subController + " - " + e.toString());
            // If any goes wrong, ignore just load we don't want the
            // aplication break because of a internal sub view
            return null;
        }
    }

    /**
     * Gets the edits the controller.
     * @return the edits the controller
     */
    public StateController getEditController() {
        return this.states.get(State.EDIT);
    }

    /**
     * Gets the list controller.
     * @return the list controller
     */
    public StateController getListController() {
        return this.states.get(State.LIST);
    }

    /**
     * Gets the creates the controller.
     * @return the creates the controller
     */
    public StateController getCreateController() {
        return this.states.get(State.CREATE);
    }

    /**
     * Gets the active controller.
     * @return the active controller
     */
    public StateController getActiveController() {
        return this.activeController;
    }

    /**
     * Sets the active controller.
     * @param activeController
     *            the new active controller
     */
    public void setActiveController(final StateController activeController) {
        activateController(activeController);
    }

    /**
     * Load sub view.
     * @param <T>
     *            the generic type
     * @param subController
     *            the sub controller
     * @return the t
     */
    protected <T> T loadSubView(final String subController) {
        try {
            final Result loadResult = this.fxmlLoader.load(MachineStatesController.class
                    .getResource(subController));
            final T controller = loadResult.getController();
            return controller;
        } catch (final IOException e) {
            throw new RuntimeException("Can't Load the subcontroller " + subController
                    + " caused by: " + e);
        }

    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.controllers.ContentController#onQuit()
     */
    @Override
    public void onQuit() {
        this.states.keySet().forEach(s -> {
            try {
                this.states.get(s).onQuit();
            } catch (final Exception e) {/* nothing */}
        });

        super.onQuit();
    }

    /**
     * Gets the content.
     * @return the content
     */
    public abstract Pane getContent();

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.controllers.ContentController#getRootNode()
     */
    @Override
    public abstract Node getRootNode();

}
