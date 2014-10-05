package org.xine.qtime.entities;

import java.io.Serializable;

/**
 * The Class Preference.
 */
public class Preference implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The type. */
    private PreferenceType type;

    /**
     * Gets the type.
     * @return the type
     */
    public PreferenceType getType() {
        return this.type;
    }

    /**
     * Sets the type.
     * @param type
     *            the new type
     */
    public void setType(final PreferenceType type) {
        this.type = type;
    }

}
