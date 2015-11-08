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


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * The Class StateController.
 * @param <T>
 *            the generic type
 */
public abstract class StateController<T> extends ContentController implements StateControllable<T>{

    /** The machine states controller. */
    private MachineStatesController<T> machineStatesController;

    /** The selected. */
    private final ObjectProperty<T> selected = new SimpleObjectProperty<>();

    /**
     * Gets the machine states controller.
     * @return the machine states controller
     */
    public MachineStatesController<T> getMachineStatesController() {
        return this.machineStatesController;
    }

    /**
     * Sets the machine states controller.
     * @param machineStatesController
     *            the new machine states controller
     */
    public void setMachineStatesController(final MachineStatesController<T> machineStatesController) {
        this.machineStatesController = machineStatesController;
    }

    /**
     * Selected property.
     * @return the object property
     */
    public final ObjectProperty<T> selectedProperty() {
        return this.selected;
    }

    /**
     * Gets the selected.
     * @return the selected
     */
    public final T getSelected() {
        return this.selectedProperty().get();
    }

    /**
     * Sets the selected.
     * @param selected
     *            the new selected
     */
    public final void setSelected(final T selected) {
        this.selectedProperty().set(selected);
    }

}
