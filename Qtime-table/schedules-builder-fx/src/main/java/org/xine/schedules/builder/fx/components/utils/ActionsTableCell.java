package org.xine.schedules.builder.fx.components.utils;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

/**
 * The Class ActionsTableCell.
 * @param <T>
 *            the generic type
 * @param <S>
 *            the generic type
 */
public class ActionsTableCell<T, S> extends TableCell<T, S> {

    /** The box. */
    private final HBox box = new HBox(10);

    /** The view button. */
    private final Button viewButton = new Button("V");

    /** The edit button. */
    private final Button editButton = new Button("E");

    /** The delete button. */
    private final Button deleteButton = new Button("D");

    /**
     * Display button if the row is not empty.
     * @param item
     *            the item
     * @param empty
     *            the empty
     */
    @Override
    protected void updateItem(final S item, final boolean empty) {
        super.updateItem(item, empty);

        if (!empty) {
            setGraphic(this.box);
        }
    }

    /**
     * Instantiates a new actions table cell.
     */
    public ActionsTableCell() {
        super();

        this.box.getChildren().add(this.viewButton);
        this.box.getChildren().add(this.editButton);
        this.box.getChildren().add(this.deleteButton);

        this.viewButton.setOnAction(e -> onView());
        this.editButton.setOnAction(e -> onEdit());
        this.deleteButton.setOnAction(e -> onDelete());

    }

    /**
     * On view.
     */
    private void onView() {
        final ActionsTableCellViewEvent<S> e = new ActionsTableCellViewEvent<>(this.getData());
        Event.fireEvent(this, e);

    }

    /**
     * On edit.
     */
    private void onEdit() {
        fireEvent(new ActionsTableCellEditEvent<>(this.getData()));
    }

    /**
     * On delete.
     */
    private void onDelete() {
        final ActionsTableCellDeleteEvent<S> e = new ActionsTableCellDeleteEvent<>(this.getData());
        Event.fireEvent(this, e);
    }

    @SuppressWarnings({"unchecked", "hiding" })
    public <S> S getData() {
        return (S) getItem();
    }

}