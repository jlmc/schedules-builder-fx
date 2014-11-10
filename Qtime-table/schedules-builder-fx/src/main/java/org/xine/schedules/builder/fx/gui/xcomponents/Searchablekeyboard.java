package org.xine.schedules.builder.fx.gui.xcomponents;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import org.xine.fx.guice.FXMLComponent;

/**
 * The Class Searchablekeyboard.
 * @param <T>
 *            the generic type
 */
@FXMLComponent(resources = "org.xine.schedules.builder.fx.gui.xcomponents.Searchablekeyboard")
public class Searchablekeyboard<T> extends AnchorPane {

    /*
     * *************************************************************************
     * *
     * Variables and Components *
     * *
     * ************************************************************************.
     */

    /** The root. */
    @FXML
    private AnchorPane root;

    /** The button. */
    @FXML
    private Button button;

    /** The textfield. */
    @FXML
    private TextField textfield;

    /** The choise box. */
    @FXML
    private ChoiceBox<T> choiseBox;

    /** The searchable action. */
    private final ObjectProperty<EventHandler<ActionEvent>> searchableAction = new SimpleObjectProperty<>();

    /** The choise types. */
    private final ListProperty<T> choiseTypes = new SimpleListProperty<>(FXCollections.observableArrayList());

    /**
     * *************************************************************************
     * *
     * Constructors *
     * *
     * ************************************************************************.
     */
    public Searchablekeyboard() {
        super();
    }

    /**
     * Initialize.
     */
    // @FXML
    public void initialize() {
        System.out.println("test");
        this.textfield.setPromptText("put here the searchable text...");

        // this.textfield.setOnKeyPressed(new EventHandler<KeyEvent>() ...
        this.textfield.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (this.textfield.getText() != null && !this.textfield.getText().trim().isEmpty()) {
                    getSearchableAction().handle(new ActionEvent(event.getSource(), event.getTarget()));
                }
            }
        });

        // this.button.setOnAction(new EventHandler<ActionEvent>()...
        this.button.setOnAction(event -> {
            if (this.textfield.getText() != null && !this.textfield.getText().trim().isEmpty()) {
                getSearchableAction().handle(event);
            }
        });

        this.choiseBox.setItems(this.choiseTypes);

    }

    /*
     * *************************************************************************
     * *
     * Properties *
     * *
     * ***********************************************************************.
     */

    /**
     * Sets the searchable action.
     * @param handler
     *            the new searchable action
     */
    public final void setSearchableAction(final EventHandler<ActionEvent> handler) {
        this.searchableAction.set(handler);
    }

    /**
     * Gets the searchable action.
     * @return the searchable action
     */
    public final EventHandler<ActionEvent> getSearchableAction() {
        return this.searchableAction.get();

    }

    /**
     * Gets the choise types.
     * @return the choise types
     */
    public ListProperty<T> getChoiseTypes() {
        return this.choiseTypes;
    }

    public T getSelectedChoiseType() {
        return this.choiseBox.getValue();
    }

    /*
     * *************************************************************************
     * *
     * Methods *
     * *
     * ************************************************************************.
     */

    /**
     * Gets the root node.
     * @return the root node
     */
    public Node getRootNode() {
        return this.root;
    }

}
