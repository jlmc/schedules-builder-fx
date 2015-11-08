/*
 * Copyright (c) 2012 Spout LLC <http://www.spout.org>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */
/* 
 * Copyright (c) 2015 Qxine <https://github.com/jlmc>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */
package org.xine.qtime.fxdesktop.controllers;

import org.xine.fx.guice.GuiceFXMLLoader;
import org.xine.fx.guice.GuiceFXMLLoader.Result;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import javax.inject.Inject;

/**
 * The Class MachineStatesController.
 * @param <T>
 *            the generic type
 */
public abstract class MachineStatesController<T> extends ContentController implements
        MachineStatesControllable<T> {

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
    private final Map<State, StateController<T>> states = new HashMap<>();

    /** The active controller. */
    private StateController<T> activeController;

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
     * Inits the states. Called to initialized all state controllers defined on
     * the Map views parameters,
     * @param views
     *            the views map, with the path to the views
     * @param location
     *            The location used to resolve relative paths for the root
     *            object, or <tt>null</tt> if the location is not known.
     * @param resources
     *            The resources used to localize the root object, or <tt>null</tt> if the root
     *            object was not localized.
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
                loadSubController(state, subviews.get(state), resources);
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
    private void activateController(final StateController<T> contentController) {
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
     * @param resources
     *            the resources
     * @return the content controller
     */
    private ContentController loadSubController(final State state, final String subController,
            final ResourceBundle resources) {
        try {
            final StateController<T> controller = loadSubView(subController, resources);
            controller.setMachineStatesController(this);
            this.states.put(state, controller);
            controller.setApplicationController(getApplicationController());
            if (getContent() != null) {
                getContent().getChildren().add(controller.getRootNode());
                controller.setControllerConstrains();
                controller.getRootNode().setVisible(false);
            }
            return controller;
        } catch (final Exception e) {
            System.out.println("SubController OF:" + subController + " - " + e.toString());
            // If any goes wrong, ignore just load we don't want the
            // application break because of a internal sub view
            return null;
        }
    }

    /**
     * Gets the edits the controller.
     * @return the edits the controller
     */
    public StateController<T> getEditController() {
        return this.states.get(State.EDIT);
    }

    /**
     * Gets the list controller.
     * @return the list controller
     */
    public StateController<T> getListController() {
        return this.states.get(State.LIST);
    }

    /**
     * Gets the creates the controller.
     * @return the creates the controller
     */
    public StateController<T> getCreateController() {
        return this.states.get(State.CREATE);
    }

    /**
     * Gets the active controller.
     * @return the active controller
     */
    public StateController<T> getActiveController() {
        return this.activeController;
    }

    /**
     * Sets the active controller.
     * @param activeController
     *            the new active controller
     */
    public void setActiveController(final StateController<T> activeController) {
        activateController(activeController);
    }

    /**
     * On back.
     */
    @Override
    public void onBack() {
        activateController(getListController());
    }

    /**
     * On added.
     * @param objs
     *            the objs
     */
    @Override
    public void onAdded(final Collection<T> objs) {
        this.activateController(getListController());
        getListController().onEntityAdded(objs);
    }

    /**
     * On removed.
     * @param objs
     *            the objs
     */
    @Override
    public void onRemoved(final Collection<T> objs) {
        this.activateController(getListController());
        getListController().onEntityRemoved(objs);
    }

    /**
     * On edited.
     * @param obj
     *            the obj
     */
    @Override
    public void onEdited(final T obj) {
        this.activateController(getListController());
        getListController().onEntityEdited(obj);
    }

    /**
     * Load sub view.
     * @param <C>
     *            the generic type
     * @param subController
     *            the sub controller
     * @param resources
     *            the resources
     * @return the t
     */
    protected <C> C loadSubView(final String subController, final ResourceBundle resources) {
        try {
            final Result loadResult = this.fxmlLoader.load(
                    MachineStatesController.class.getResource(subController), resources);
            final C controller = loadResult.getController();
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
            } catch (final Exception e) {
                /**/
            }
        });
        super.onQuit();
    }

    /*
     * (non-Javadoc)
     * @see
     * org.xine.qtime.fxdesktop.controllers.ContentController#setApplicationController(org.xine.
     * qtime.fxdesktop.controllers.ApplicationController)
     */
    @Override
    public void setApplicationController(final ApplicationController applicationController) {
        super.setApplicationController(applicationController);
        this.states
                .keySet()
                .parallelStream()
                .forEach(
                        state -> this.states.get(state).setApplicationController(
                                applicationController));
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
