package org.xine.qtime.entities;

import java.io.Serializable;

/**
 * The Class Departament.
 */
public class Departament implements Serializable {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    private Long id;

    /** The name. */
    private String name;

    /** The acronym. */
    private String acronym;

    /** The description. */
    private String description;

    /**
     * Gets the acronym.
     * @return the acronym
     */
    public String getAcronym() {
        return this.acronym;
    }

    /**
     * Gets the description.
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the id.
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the acronym.
     * @param acronym
     *            the new acronym
     */
    public void setAcronym(final String acronym) {
        this.acronym = acronym;
    }

    /**
     * Sets the description.
     * @param description
     *            the new description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Sets the id.
     * @param id
     *            the new id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Sets the name.
     * @param name
     *            the new name
     */
    public void setName(final String name) {
        this.name = name;
    }

}
