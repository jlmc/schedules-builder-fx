package org.xine.schedules.builder.fx.model;

import java.io.Serializable;
import java.util.Objects;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class Person.
 */
public class Person implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The name. */
    private final SimpleStringProperty name = new SimpleStringProperty(null);

    /** The lunch. */
    private final SimpleBooleanProperty lunch = new SimpleBooleanProperty(false);

    /** The tour. */
    private final SimpleBooleanProperty tour = new SimpleBooleanProperty(false);

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
        return this.name.get();
    }

    /**
     * Name property.
     * @return the string property
     */
    public StringProperty nameProperty() {
        return this.name;
    }

    /**
     * Checks if is lunch.
     * @return true, if is lunch
     */
    public boolean isLunch() {
        return this.lunch.get();
    }

    /**
     * Checks if is tour.
     * @return true, if is tour
     */
    public boolean isTour() {
        return this.tour.get();
    }

    /**
     * Lunch property.
     * @return the boolean property
     */
    public BooleanProperty lunchProperty() {
        return this.lunch;
    }

    /**
     * Tour property.
     * @return the boolean property
     */
    public BooleanProperty tourProperty() {
        return this.tour;
    }

    /**
     * Sets the lunch.
     * @param lunch
     *            the new lunch
     */
    public void setLunch(final boolean lunch) {
        this.lunch.set(lunch);
    }

    /**
     * Sets the tout.
     * @param tour
     *            the new tout
     */
    public void setTout(final boolean tour) {
        this.tour.set(tour);
    }

    /**
     * Sets the name.
     * @param name
     *            the new name
     */
    public void setName(final String name) {
        this.name.set(name);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        if (getName() == null) {
            return Objects.hashCode("");
        }
        return Objects.hashCode(getName().trim().toUpperCase());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;

        if (getName() == null) {
            if (other.getName() != null) {
                return false;
            }
        } else if (!getName().trim().equalsIgnoreCase(other.getName().trim())) {
            return false;
        }
        return true;
    }

}
