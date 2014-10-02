package org.xine.schedules.builder.fx.components.subjects.components;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.xine.schedules.builder.fx.model.Subject;

/**
 * The Class SubjectsModel.
 */
public class SubjectsModel {

    /** The subjects. */
    private final ObservableList<Subject> subjects = FXCollections.observableArrayList();

    /**
     * Gets the person data.
     * @return the person data
     */
    public ObservableList<Subject> getSubjectsData() {
        return this.subjects;
    }

    /**
     * Sets the subjects data.
     * @param subjects
     *            the new subjects data
     */
    public void setSubjectsData(final List<Subject> subjects) {
        this.subjects.clear();
        this.subjects.addAll(subjects);
    }

}
