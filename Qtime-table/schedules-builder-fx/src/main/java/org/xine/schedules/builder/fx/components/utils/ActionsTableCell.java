package org.xine.schedules.builder.fx.components.utils;

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

    public ActionsTableCell() {
        super();

        this.box.getChildren().add(this.viewButton);
        this.box.getChildren().add(this.editButton);
        this.box.getChildren().add(this.deleteButton);

    }

}
