package org.xine.schedules.builder.fx.components.rooms;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

import org.xine.schedules.builder.fx.model.Room;

/**
 * The Class RoomsDataModel.
 */
public class RoomsDataModel {

    /** The rooms. */
    private final ListProperty<Room> rooms = new SimpleListProperty<>(FXCollections.observableArrayList());

    /** The selected. */
    private final ObjectProperty<Room> selected = new SimpleObjectProperty<>();

    /**
     * Gets the rooms.
     * @return the rooms
     */
    public ListProperty<Room> getRooms() {
        return this.rooms;
    }

    /**
     * Gets the selected.
     * @return the selected
     */
    public Room getSelected() {
        return this.selected.get();
    }

    /**
     * Sets the selected.
     * @param selected
     *            the new selected
     */
    public void setSelected(final Room selected) {
        this.selected.set(selected);
    }

    /**
     * Gets the selected property.
     * @return the selected property
     */
    public ObjectProperty<Room> getSelectedProperty() {
        return this.selected;
    }

}
