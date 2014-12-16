package org.xine.qtime.fxdesktop.controllers;

/**
 * The Class StateController.
 */
public abstract class StateController extends ContentController {

    /** The machine states controller. */
    private MachineStatesController machineStatesController;

    /**
     * Gets the machine states controller.
     * @return the machine states controller
     */
    public MachineStatesController getMachineStatesController() {
        return this.machineStatesController;
    }

    /**
     * Sets the machine states controller.
     * @param machineStatesController
     *            the new machine states controller
     */
    public void setMachineStatesController(final MachineStatesController machineStatesController) {
        this.machineStatesController = machineStatesController;
    }

}
