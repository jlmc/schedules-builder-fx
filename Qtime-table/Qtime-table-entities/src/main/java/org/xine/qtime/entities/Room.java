package org.xine.qtime.entities;

import java.io.Serializable;

/**
 * The Class Room.
 */
public class Room implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    private long id;

    /** The seat number. */
    private int seatNumber;

    /** The type. */
    private RoomType type;

    /**
     * Gets the id.
     * @return the id
     */
    public long getId() {
        return this.id;
    }

    /**
     * Sets the id.
     * @param id
     *            the new id
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Gets the seat number.
     * @return the seat number
     */
    public int getSeatNumber() {
        return this.seatNumber;
    }

    /**
     * Sets the seat number.
     * @param seatNumber
     *            the new seat number
     */
    public void setSeatNumber(final int seatNumber) {
        this.seatNumber = seatNumber;
    }

    /**
     * Gets the type.
     * @return the type
     */
    public RoomType getType() {
        return this.type;
    }

    /**
     * Sets the type.
     * @param type
     *            the new type
     */
    public void setType(final RoomType type) {
        this.type = type;
    }
}
