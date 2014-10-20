package org.xine.schedules.builder.fx.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class Rooms.
 */
public class Rooms {
    /** The id. */
    private final IntegerProperty id = new SimpleIntegerProperty(-1);

    /** The seat number. */
    private final IntegerProperty seatNumber = new SimpleIntegerProperty(-1);

    /** The name. */
    private final StringProperty name = new SimpleStringProperty(null);

    public Rooms() {
        super();
    }

    public Rooms(final int id, final int seatNumber, final String name) {
        super();
        setId(id);
        setSeatNumber(seatNumber);
        setName(name);
    }

    /**
     * Gets the id property.
     * @return the id property
     */
    public IntegerProperty getIdProperty() {
        return this.id;
    }

    /**
     * Sets the id.
     * @param id
     *            the new id
     */
    public void setId(final int id) {
        this.id.set(id);
    }

    /**
     * Gets the id.
     * @return the id
     */
    public int getId() {
        return this.id.get();
    }

    /**
     * Gets the seat number property.
     * @return the seat number property
     */
    public IntegerProperty getSeatNumberProperty() {
        return this.seatNumber;
    }

    /**
     * Sets the seat number.
     * @param value
     *            the new seat number
     */
    public void setSeatNumber(final int value) {
        this.seatNumber.set(value);
    }

    /**
     * Gets the seat number.
     * @return the seat number
     */
    public int getSeatNumber() {
        return this.seatNumber.get();
    }

    /**
     * Gets the name property.
     * @return the name property
     */
    public StringProperty getNameProperty() {
        return this.name;
    }

    /**
     * Sets the name.
     * @param value
     *            the new name
     */
    public void setName(final String value) {
        this.name.set(value);
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
        return this.name.get();
    }

}
