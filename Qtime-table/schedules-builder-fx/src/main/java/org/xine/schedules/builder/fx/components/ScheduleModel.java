package org.xine.schedules.builder.fx.components;

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Class ScheduleModel.
 * @param <T>
 *            the generic type
 */
public class ScheduleModel<T> {

    /** The datos. */
    private final ObservableList<T> datos = FXCollections.observableArrayList();

    /** The selected object property. */
    private final ObjectProperty<T> selectedObjectProperty = new SimpleObjectProperty<>(null);

    /**
     * Gets the subjects data.
     * @return the subjects data
     */
    public ObservableList<T> getSubjectsData() {
        return this.datos;
    }

    /**
     * Sets the subjects data.
     * @param subjects
     *            the new subjects data
     */
    public void setSubjectsData(final List<T> subjects) {
        this.datos.clear();
        this.datos.addAll(subjects);
    }

    /**
     * Gets the selected object property.
     * @return the selected object property
     */
    public ObjectProperty<T> getSelectedObjectProperty() {
        return this.selectedObjectProperty;
    }

    /**
     * Sets the selected object.
     * @param object
     *            the new selected object
     */
    public void setSelectedObject(final T object) {
        this.selectedObjectProperty.set(object);
    }

    /**
     * Gets the selected object.
     * @return the selected object
     */
    public T getSelectedObject() {
        return this.selectedObjectProperty.get();
    }

}
