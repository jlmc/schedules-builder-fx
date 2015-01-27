package org.xine.qtime.fxdesktop.controllers;

import java.util.Collection;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * The Class StateController.
 * @param <T>
 *            the generic type
 */
public abstract class StateController<T> extends ContentController {

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
     * Adds the.
     * @param objs
     *            the objs
     */
    public void add(final Collection<T> objs) {
        //
    }

    /**
     * Removes the.
     * @param objs
     *            the objs
     */
    public void remove(final Collection<T> objs) {
        // nothing
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
