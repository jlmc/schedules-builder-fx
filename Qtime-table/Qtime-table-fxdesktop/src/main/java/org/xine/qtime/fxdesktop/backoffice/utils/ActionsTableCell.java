/*
 * Copyright (c) 2012 Spout LLC <http://www.spout.org>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */
package org.xine.qtime.fxdesktop.backoffice.utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private final HBox box = new HBox(5);

    /** The view button. */
    private final Button viewButton = new Button();

    /** The edit button. */
    private final Button editButton = new Button();

    /** The delete button. */

    private final Button deleteButton = new Button();

    /** The Constant DELETE_IMG. */
    private static final String DELETE_IMG = "/images/delete-16.png";

    /** The Constant EDIT_IMG. */
    private static final String EDIT_IMG = "/images/edit-16.png";

    /** The Constant VIEW_IMG. */
    private static final String VIEW_IMG = "/images/eye-16.png";

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
        } else {
            setGraphic(null);
        }
    }

    /**
     * Instantiates a new actions table cell.
     * @param viewIsVisible
     *            the view is visible
     */
    public ActionsTableCell(final boolean viewIsVisible) {
        super();

        this.box.getStyleClass().add("rigth-alignment");

        if (viewIsVisible) {
            this.box.getChildren().add(this.viewButton);
            this.viewButton.setGraphic(this.getImage(VIEW_IMG));
            this.viewButton.setTooltip(new Tooltip("view this record"));
        }

        this.box.getChildren().add(this.editButton);
        this.box.getChildren().add(this.deleteButton);

        // this.deleteButton.setPrefSize(15D, 15D);
        this.deleteButton.setGraphic(this.getImage(DELETE_IMG));
        this.editButton.setGraphic(this.getImage(EDIT_IMG));

        this.editButton.setTooltip(new Tooltip("Edit this record"));
        this.deleteButton.setTooltip(new Tooltip("Delete this record"));

        // this.viewButton.setOnAction(e -> onView());
        // this.editButton.setOnAction(e -> onEdit());
        // this.deleteButton.setOnAction(e -> onDelete());
    }

    /**
     * Instantiates a new actions table cell.
     */
    public ActionsTableCell() {
        this(true);

    }

    /**
     * Gets the image.
     * @param in
     *            the in
     * @return the image
     */
    private ImageView getImage(final String in) {
        try {
            final Image img = new javafx.scene.image.Image(this.getClass().getResourceAsStream(in));

            final ImageView iv = new ImageView(img);
            iv.maxWidth(20.0D);
            iv.maxHeight(20.0D);
            return iv;
        } catch (final Exception e) {
            return null;
        }
    }

    // private void onView() {
    // final ActionsTableCellViewEvent<S> e = new ActionsTableCellViewEvent<>(this.getData());
    // Event.fireEvent(this, e);
    //
    // }

    // private void onEdit() {
    // fireEvent(new ActionsTableCellEditEvent<>(this.getData()));
    // }

    // actionsTableCell.addEventHandler(ActionsTableCellEditEvent.EDIT_EVENT, e -> {
    // System.out.println("edit clicked");
    // });

    // private void onDelete() {
    // final ActionsTableCellDeleteEvent<S> e = new ActionsTableCellDeleteEvent<>(this.getData());
    // Event.fireEvent(this, e);
    // }

    /**
     * Gets the data.
     * @param <S>
     *            the generic type
     * @return the data
     */
    @SuppressWarnings({"unchecked", "hiding" })
    public <S> S getData() {
        return (S) getItem();
    }

    /**
     * Sets the on view action.
     * @param value
     *            the new on view action
     */
    public final void setOnViewAction(final EventHandler<ActionEvent> value) {
        this.viewButton.setOnAction(value);
    }

    /**
     * Gets the on view action.
     * @return the on view action
     */
    public final EventHandler<ActionEvent> getOnViewAction() {
        return this.viewButton.getOnAction();
    }

    /**
     * Sets the on delete action.
     * @param value
     *            the new on delete action
     */
    public final void setOnDeleteAction(final EventHandler<ActionEvent> value) {
        this.deleteButton.setOnAction(value);
    }

    /**
     * Gets the on delete action.
     * @return the on delete action
     */
    public final EventHandler<ActionEvent> getOnDeleteAction() {
        return this.deleteButton.getOnAction();
    }

    /**
     * Gets the on edit action.
     * @return the on edit action
     */
    public final EventHandler<ActionEvent> getOnEditAction() {
        return this.editButton.getOnAction();
    }

    /**
     * Sets the on edit action.
     * @param value
     *            the new on edit action
     */
    public final void setOnEditAction(final EventHandler<ActionEvent> value) {
        this.editButton.setOnAction(value);
    }

    /**
     * Sets the on view tooltip.
     * @param text
     *            the new on view tooltip
     */
    public void setOnViewTooltip(final String text) {
        this.viewButton.setTooltip(new Tooltip(text));
    }

    /**
     * Sets the on edit tooltip.
     * @param text
     *            the new on edit tooltip
     */
    public void setOnEditTooltip(final String text) {
        this.editButton.setTooltip(new Tooltip(text));
    }

    /**
     * Sets the on delete tooltip.
     * @param text
     *            the new on delete tooltip
     */
    public void setOnDeleteTooltip(final String text) {
        this.deleteButton.setTooltip(new Tooltip(text));
    }

}
