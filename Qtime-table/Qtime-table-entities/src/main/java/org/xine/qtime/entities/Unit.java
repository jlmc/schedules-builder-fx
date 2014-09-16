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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class Unit.
 */
@Entity
@Table(name = "UNIT_CURRICULUM")
public class Unit implements Serializable {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The credits. */
    @Column(name = "credits", nullable = false)
    private int credits;

    /** The subject. */
    @ManyToOne
    @JoinColumn(name = "subjectId", nullable = false)
    private Subject subject;

    /** The blocks. */
    @OneToMany(targetEntity = Block.class, mappedBy = "unit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final Set<Block> blocks = new HashSet<>(0);

    /** The study plans. */
    @ManyToOne
    @JoinColumn(name = "studyPlanId", nullable = false)
    private StudyPlan studyPlan;

    // @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JoinTable(name = "studyPlanUnits", joinColumns = { @JoinColumn(name =
    // "unitId", nullable = false, updatable = false) }, inverseJoinColumns = {
    // @JoinColumn(name = "unitId") })

    /**
     * Instantiates a new unit.
     */
    public Unit() {
        super();
    }

    /**
     * Instantiates a new unit.
     * @param id
     *            the id
     * @param credits
     *            the credits
     * @param subject
     *            the subject
     * @param studyPlan
     *            the study plan
     */
    public Unit(final Long id, final int credits, final Subject subject, final StudyPlan studyPlan) {
        super();
        this.id = id;
        this.credits = credits;
        this.subject = subject;
        this.studyPlan = studyPlan;
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
     * Gets the subject.
     * @return the subject
     */
    public Subject getSubject() {
        return this.subject;
    }

    /**
     * Sets the subject.
     * @param subject
     *            the new subject
     */
    public void setSubject(final Subject subject) {
        this.subject = subject;
    }

    /**
     * Gets the credits.
     * @return the credits
     */
    public int getCredits() {
        return this.credits;
    }

    /**
     * Sets the credits.
     * @param credits
     *            the new credits
     */
    public void setCredits(final int credits) {
        this.credits = credits;
    }

    /**
     * Gets the study plan.
     * @return the study plan
     */
    public StudyPlan getStudyPlan() {
        return this.studyPlan;
    }

    /**
     * Sets the study plan.
     * @param studyPlan
     *            the new study plan
     */
    public void setStudyPlan(final StudyPlan studyPlan) {
        this.studyPlan = studyPlan;
    }

    /**
     * Gets the blocks.
     * @return the blocks
     */
    public Set<Block> getBlocks() {
        return this.blocks;
    }

}
