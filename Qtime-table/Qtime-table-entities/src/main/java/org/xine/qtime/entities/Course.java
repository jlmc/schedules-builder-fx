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
 * The Class Course.
 */
@Entity
@Table(name = "COURSE")
public class Course implements Serializable {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The name. */
    @Column(name = "name", length = 128, nullable = false, unique = true)
    private String name;

    /** The description. */
    @Column(name = "description", length = 256)
    private String description;

    /** The study plans. */
    @OneToMany(targetEntity = StudyPlan.class, mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<StudyPlan> studyPlans = new HashSet<>(0);

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
     * Gets the study plans.
     * @return the study plans
     */
    public Set<StudyPlan> getStudyPlans() {
        return this.studyPlans;
    }

    /**
     * Sets the study plans.
     * @param studyPlans
     *            the new study plans
     */
    public void setStudyPlans(final Set<StudyPlan> studyPlans) {
        this.studyPlans = studyPlans;
    }

}
