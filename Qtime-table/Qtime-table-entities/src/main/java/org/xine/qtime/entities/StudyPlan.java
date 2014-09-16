package org.xine.qtime.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class StudyPlan.
 */
@Entity
@Table(name = "STUDY_PLAN")
public class StudyPlan implements Serializable {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The course. */
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    /** The study plan detail. */
    @ManyToOne
    @JoinColumn(name = "studyPlanDetailId")
    private StudyPlanDetail studyPlanDetail;

    /** The units. */
    private Set<Unit> units;

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
     * Gets the units.
     * @return the units
     */
    public Set<Unit> getUnits() {
        return this.units;
    }

    /**
     * Sets the units.
     * @param units
     *            the new units
     */
    public void setUnits(final Set<Unit> units) {
        this.units = units;
    }

    /**
     * Gets the study plan detail.
     * @return the study plan detail
     */
    public StudyPlanDetail getStudyPlanDetail() {
        return this.studyPlanDetail;
    }

    /**
     * Sets the study plan detail.
     * @param studyPlanDetail
     *            the new study plan detail
     */
    public void setStudyPlanDetail(final StudyPlanDetail studyPlanDetail) {
        this.studyPlanDetail = studyPlanDetail;
    }

    /**
     * Gets the course.
     * @return the course
     */
    public Course getCourse() {
        return this.course;
    }

    /**
     * Sets the course.
     * @param course
     *            the new course
     */
    public void setCourse(final Course course) {
        this.course = course;
    }

}
