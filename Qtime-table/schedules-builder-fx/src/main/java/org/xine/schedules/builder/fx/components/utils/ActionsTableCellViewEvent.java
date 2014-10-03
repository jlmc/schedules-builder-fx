package org.xine.schedules.builder.fx.components.utils;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * The Class ActionsTableCellViewEvent.
 * @param <T>
 *            the generic type
 */
public class ActionsTableCellViewEvent<T> extends Event {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The data. */
    private final T data;

    /** The Constant VIEW_EVENT. */
    public static final EventType<ActionsTableCellViewEvent<?>> VIEW_EVENT = new EventType<>(Event.ANY, "VIEW_EVENT");

    /**
     * Instantiates a new actions table cell view event.
     * @param data
     *            the data
     */
    public ActionsTableCellViewEvent(final T data) {
        super(VIEW_EVENT);
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
