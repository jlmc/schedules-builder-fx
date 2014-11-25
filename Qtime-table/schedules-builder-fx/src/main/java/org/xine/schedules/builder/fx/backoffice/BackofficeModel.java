package org.xine.schedules.builder.fx.backoffice;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Class BackofficeModel.
 * @param <T>
 *            the generic type
 */
public class BackofficeModel<T> {

    /** The list. */
    private ListProperty<T> list;// = FXCollections.observableArrayList();

    /** The selected. */
    private final ObjectProperty<T> selected = new SimpleObjectProperty<>();

    /**
     * Gets the list.
     * @return the list
     */
    public ListProperty<T> getList() {
        if (this.list == null) {
            final ObservableList<T> innerList = FXCollections.observableArrayList();
            this.list = new SimpleListProperty<>(innerList);
        }
        return this.list;
    }

    /**
     * Selected property.
     * @return the object property
     */
    public final ObjectProperty<T> selectedProperty() {
        return this.selected;
    }

    /**
     * Gets the selected.
     * @return the selected
     */
    public final T getSelected() {
        return this.selectedProperty().get();
    }

    /**
     * Sets the selected.
     * @param selected
     *            the new selected
     */
    public final void setSelected(final T selected) {
        this.selectedProperty().set(selected);
    }

}
