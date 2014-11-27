package org.xine.schedules.builder.fx.backoffice;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import org.xine.schedules.builder.fx.gui.ContentController;
import org.xine.schedules.builder.fx.gui.ContentDecorated;

/**
 * The Class BackofficeContentController.
 * @param <T>
 *            the generic type
 */
public abstract class BackofficeContentController<T> extends ContentController {

    /** The content decorated. */
    private ContentDecorated contentDecorated;

    /** The model. */
    private final ObjectProperty<T> selected = new SimpleObjectProperty<>();

    /**
     * Gets the content decorated.
     * @return the content decorated
     */
    public ContentDecorated getContentDecorated() {
        return this.contentDecorated;
    }

    /**
     * Sets the content decorated.
     * @param contentDecorated
     *            the new content decorated
     */
    public void setContentDecorated(final ContentDecorated contentDecorated) {
        this.contentDecorated = contentDecorated;
    }

    /**
     * Gets the model property.
     * @return the model property
     */
    public ObjectProperty<T> getSelectedProperty() {
        return this.selected;
    }

    /**
     * Gets the model.
     * @return the model
     */
    public T getSelected() {
        return this.selected.get();
    }

    /**
     * Sets the model.
     * @param value
     *            the new model
     */
    public void setSelected(final T value) {
        this.selected.set(value);
    }

}
