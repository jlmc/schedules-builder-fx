package org.xine.schedules.builder.fx.components.rooms;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import org.xine.schedules.builder.fx.model.Room;

public class RoomsDataModel {

    private final ListProperty<Room> rooms = new SimpleListProperty<Room>(FXCollections.observableArrayList());

    private Room selected;

    public Room getSelected() {
        return this.selected;
    }

    public void setSelected(final Room selected) {
        this.selected = selected;
    }

}
