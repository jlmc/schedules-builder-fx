package org.xine.qtime.entities;

import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;

/**
 * The Class StudyPlanDetail.
 */
@Entity
@Table(name = "StudyPlanDetail", uniqueConstraints = @UniqueConstraint(columnNames = {"year", "period" }))
public class StudyPlanDetail implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The year. */
    @Column(name = "year")
    private int year;

    /** The period. */
    @Column(name = "period")
    private int period;

    /** The study plans. */
    @OneToMany(targetEntity = StudyPlan.class, mappedBy = "studyPlanDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<StudyPlan> studyPlans;

    /**
     * Instantiates a new study plan detail.
     */
    public StudyPlanDetail() {
        super();
    }

    /**
     * Instantiates a new study plan detail.
     * @param year
     *            the year
     * @param period
     *            the period
     */
    public StudyPlanDetail(final int year, final int period) {
        this.year = year;
        this.period = period;
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
        final StudyPlanDetail other = (StudyPlanDetail) obj;
        if (this.period != other.period) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        return true;
    }

    /**
     * Gets the id.
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Gets the period.
     * @return the period
     */
    public int getPeriod() {
        return this.period;
    }

    /**
     * Gets the study plans.
     * @return the study plans
     */
    public Set<StudyPlan> getStudyPlans() {
        return this.studyPlans;
    }

    /**
     * Gets the year.
     * @return the year
     */
    public int getYear() {
        return this.year;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.period;
        result = prime * result + this.year;
        return result;
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
     * Sets the period.
     * @param period
     *            the new period
     */
    public void setPeriod(final int period) {
        this.period = period;
    }

    /**
     * Sets the study plans.
     * @param studyPlans
     *            the new study plans
     */
    public void setStudyPlans(final Set<StudyPlan> studyPlans) {
        this.studyPlans = studyPlans;
    }

    /**
     * Sets the year.
     * @param year
     *            the new year
     */
    public void setYear(final int year) {
        this.year = year;
    }

}
