package org.xine.qtime.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class Block.
 */
@Entity
@Table(name = "BLOCK")
public class Block implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The unit. */
    @ManyToOne
    @JoinColumn(name = "unitId", nullable = false)
    private Unit unit;

    /** The block type. */
    @Enumerated(EnumType.STRING)
    private BlockType blockType;

    /** The duration. */
    @Column(name = "duration")
    private long duration;

    /**
     * Instantiates a new block.
     */
    public Block() {
        super();
    }

    /**
     * Instantiates a new block.
     * @param id
     *            the id
     * @param unit
     *            the unit
     * @param blockType
     *            the block type
     * @param duration
     *            the duration
     */
    public Block(final Long id, final Unit unit, final BlockType blockType, final long duration) {
        super();
        this.id = id;
        this.unit = unit;
        this.blockType = blockType;
        this.duration = duration;
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
     * Gets the unit.
     * @return the unit
     */
    public Unit getUnit() {
        return this.unit;
    }

    /**
     * Sets the unit.
     * @param unit
     *            the new unit
     */
    public void setUnit(final Unit unit) {
        this.unit = unit;
    }

    /**
     * Gets the block type.
     * @return the block type
     */
    public BlockType getBlockType() {
        return this.blockType;
    }

    /**
     * Sets the block type.
     * @param blockType
     *            the new block type
     */
    public void setBlockType(final BlockType blockType) {
        this.blockType = blockType;
    }

    /**
     * Gets the duration.
     * @return the duration
     */
    public long getDuration() {
        return this.duration;
    }

    /**
     * Sets the duration.
     * @param duration
     *            the new duration
     */
    public void setDuration(final long duration) {
        this.duration = duration;
    }

}
