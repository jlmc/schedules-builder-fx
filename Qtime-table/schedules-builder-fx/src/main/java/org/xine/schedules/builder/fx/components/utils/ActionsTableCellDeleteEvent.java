package org.xine.schedules.builder.fx.components.utils;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * The Class ActionsTableCellEvent.
 * @param <T>
 *            the generic type
 */
public class ActionsTableCellDeleteEvent<T> extends Event {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The Constant DELETE_EVENT. */
    public static final EventType<ActionsTableCellDeleteEvent<?>> DELETE_EVENT = new EventType<>(Event.ANY, "DELETE_EVENT");

    /** The data. */
    private final T data;

    /**
     * Instantiates a new actions table cell event.
     * @param data
     *            the data
     */
    public ActionsTableCellDeleteEvent(final T data) {
        super(DELETE_EVENT);
        this.data = data;
    }

    /**
     * Gets the data.
     * @return the data
     */
    public T getData() {
        return this.data;
    }
}
