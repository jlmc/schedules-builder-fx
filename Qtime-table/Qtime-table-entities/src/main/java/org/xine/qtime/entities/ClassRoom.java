package org.xine.qtime.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The Class ClassRoom.
 */
@Entity(name = "ClassRoom")
public class ClassRoom implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    private Long id;

    /** The code. */
    private String code;

    /** The capacity. */
    private Integer capacity;

    /** The computed. */
    private Boolean computed;

    /** The category. */
    private ClassRoomCategory category;

    /**
     * Instantiates a new class room.
     */
    public ClassRoom() {
        super();
    }

    /**
     * Instantiates a new class room.
     * @param id
     *            the id
     * @param code
     *            the code
     * @param capacity
     *            the capacity
     * @param computed
     *            the computed
     * @param category
     *            the category
     */
    public ClassRoom(final Long id, final String code, final Integer capacity,
            final Boolean computed, final ClassRoomCategory category) {
        super();
        this.id = id;
        this.code = code;
        this.capacity = capacity;
        this.computed = computed;
        this.category = category;
    }

    /**
     * Instantiates a new class room.
     * @param code
     *            the code
     * @param capacity
     *            the capacity
     * @param computed
     *            the computed
     * @param category
     *            the category
     */
    public ClassRoom(final String code, final Integer capacity, final Boolean computed,
            final ClassRoomCategory category) {
        super();
        this.code = code;
        this.capacity = capacity;
        this.computed = computed;
        this.category = category;
    }

    /**
     * Gets the id.
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * Gets the code.
     * @return the code
     */
    @Column(name = "Code", nullable = false, unique = true, length = 10)
    public String getCode() {
        return this.code;
    }

    /**
     * Sets the code.
     * @param code
     *            the new code
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Gets the capacity.
     * @return the capacity
     */
    @Column(name = "capacity", nullable = false)
    public Integer getCapacity() {
        return this.capacity;
    }

    /**
     * Sets the capacity.
     * @param capacity
     *            the new capacity
     */
    public void setCapacity(final Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets the computed.
     * @return the computed
     */

    @Column(name = "computed")
    public Boolean getComputed() {
        return this.computed;
    }

    /**
     * Sets the computed.
     * @param computed
     *            the new computed
     */
    public void setComputed(final Boolean computed) {
        this.computed = computed;
    }

    /**
     * Gets the category.
     * @return the category
     */
    @Enumerated(EnumType.STRING)
    public ClassRoomCategory getCategory() {
        return this.category;
    }

    /**
     * Sets the category.
     * @param category
     *            the new category
     */
    public void setCategory(final ClassRoomCategory category) {
        this.category = category;
    }

}
