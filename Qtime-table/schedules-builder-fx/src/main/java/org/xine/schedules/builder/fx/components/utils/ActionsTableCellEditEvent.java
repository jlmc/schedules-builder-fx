package org.xine.schedules.builder.fx.components.utils;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * The Class ActionsTableCellEditEvent.
 * @param <T>
 *            the generic type
 */
public class ActionsTableCellEditEvent<T> extends Event {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The Constant EDIT_EVENT. */
    public static final EventType<ActionsTableCellEditEvent<?>> EDIT_EVENT = new EventType<>(Event.ANY, "EDIT_EVENT");

    /** The data. */
    private final T data;

    /**
     * Instantiates a new actions table cell edit event.
     * @param data
     *            the data
     */
    public ActionsTableCellEditEvent(final T data) {
        super(EDIT_EVENT);
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
