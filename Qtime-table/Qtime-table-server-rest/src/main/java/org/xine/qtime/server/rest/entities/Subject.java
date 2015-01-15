package org.xine.qtime.server.rest.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Subject.
 */
@XmlRootElement
public class Subject implements Serializable {
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
     * Instantiates a new subject.
     */
    public Subject() {
        super();
    }

    /**
     * Instantiates a new subject.
     * @param id
     *            the id
     * @param name
     *            the name
     * @param acronym
     *            the acronym
     * @param description
     *            the description
     */
    public Subject(final Long id, final String name, final String acronym, final String description) {
        super();
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.description = description;
    }

    /**
     * Gets the id.
     * @return the id
     */
    public Long getId() {
        return this.id;
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
     * Gets the name.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name.
     * @param name
     *            the new name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the acronym.
     * @return the acronym
     */
    public String getAcronym() {
        return this.acronym;
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
     * Gets the description.
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description.
     * @param description
     *            the new description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

}
