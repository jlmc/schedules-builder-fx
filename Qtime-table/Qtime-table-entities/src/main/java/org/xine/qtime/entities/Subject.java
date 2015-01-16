package org.xine.qtime.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class Subject.
 */
@Entity
@Table(name = "SUBJECT")
public class Subject implements Serializable {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The name. */
    @Column(name = "name", length = 128, nullable = false)
    private String name;

    /** The acronym. */
    @Column(name = "acronym", length = 15, nullable = false, unique = true)
    private String acronym;

    /** The description. */
    @Column(name = "description", length = 256)
    private String description;

    /** The units. */
    @OneToMany(targetEntity = Unit.class, mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final Set<Unit> units = new HashSet<>(0);

    /**
     * Instantiates a new subject.
     */
    public Subject(){
      super();
    }
    
    /**
     * Instantiates a new subject.
     *
     * @param id the id
     * @param name the name
     * @param acronym the acronym
     * @param description the description
     */
    public Subject(Long id, String name, String acronym, String description) {
      super();
      setId(id);
      setName(name);
      setAcronym(acronym);
      setDescription(description);
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

    /**
     * Gets the units.
     * @return the units
     */
    public Set<Unit> getUnits() {
        return this.units;
    }

}
