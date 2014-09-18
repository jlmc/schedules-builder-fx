package org.xine.schedules.builder.fx.components;

import org.xine.schedules.builder.fx.gui.ContentController;

/**
 * The Class ScheduleAbstractContentController.
 */
public abstract class ScheduleAbstractContentController extends ContentController {

    /** The parent component. */
    private ScheduleAbstractComponent parentComponent;

    /**
     * Gets the parent component.
     * @return the parent component
     */
    public ScheduleAbstractComponent getParentComponent() {
        return this.parentComponent;
    }

    /**
     * Sets the parent component.
     * @param parentComponent
     *            the new parent component
     */
    public void setParentComponent(final ScheduleAbstractComponent parentComponent) {
        this.parentComponent = parentComponent;
    }

}
