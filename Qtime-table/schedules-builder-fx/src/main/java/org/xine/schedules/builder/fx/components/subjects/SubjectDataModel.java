package org.xine.schedules.builder.fx.components.subjects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Singleton;

import org.xine.schedules.builder.fx.model.Subject;

/**
 * The Class SubjectDataModel.
 */
@Singleton
public class SubjectDataModel {

    /** The subjects. */
    private ListProperty<Subject> subjects;

    /** The selected subject index. */
    private IntegerProperty selectedSubjectIndex = new SimpleIntegerProperty(-1);

    /**
     * Gets the subject.
     * @return the subject
     */
    public ListProperty<Subject> getSubject() {
        if (this.subjects == null) {
            final ObservableList<Subject> innerList = FXCollections.observableArrayList();
            this.subjects = new SimpleListProperty<>(innerList);
        }
        return this.subjects;
    }

    /**
     * Gets the selected subject index.
     * @return the selected subject index
     */
    public int getSelectedSubjectIndex() {
        return this.selectedSubjectIndex.get();
    }

    /**
     * Sets the selected subject index.
     * @param selectedSubjectIndex
     *            the new selected subject index
     */
    public void setSelectedSubjectIndex(final int selectedSubjectIndex) {
        this.selectedSubjectIndex.set(selectedSubjectIndex);
    }

    /**
     * Selected subject index property.
     * @return the integer property
     */
    public IntegerProperty selectedSubjectIndexProperty() {
        if (this.selectedSubjectIndex == null) {
            this.selectedSubjectIndex = new SimpleIntegerProperty();
        }
        return this.selectedSubjectIndex;
    }

    /**
     * Gets the selected object.
     * @return the selected object
     */
    public Subject getSelectedObject() {
        return getSubject().get(getSelectedSubjectIndex());
    }

}
