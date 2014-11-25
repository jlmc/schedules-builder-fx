package org.xine.schedules.builder.fx.model;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class Subject.
 */
public class Subject implements Serializable {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    private final SimpleIntegerProperty id = new SimpleIntegerProperty(-1);

    /** The name. */
    private final SimpleStringProperty name = new SimpleStringProperty(null);

    public Subject() {
        super();
    }

    public Subject(final int id, final String name) {
        super();
        setId(id);
        setName(name);
    }

    /**
     * Gets the id.
     * @return the id
     */
    public int getId() {
        return this.id.get();
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
     * Sets the name.
     * @param name
     *            the new name
     */
    public void setName(final String name) {
        this.name.set(name);
    }
}
