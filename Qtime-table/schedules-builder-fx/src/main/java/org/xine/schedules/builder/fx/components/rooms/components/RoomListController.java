package org.xine.schedules.builder.fx.components.rooms.components;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.inject.Inject;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.components.ScheduleAbstractContentController;
import org.xine.schedules.builder.fx.components.rooms.RoomsDataModel;
import org.xine.schedules.builder.fx.gui.xcomponents.Searchablekeyboard;
import org.xine.schedules.builder.fx.model.Room;

@FXMLController
public class RoomListController extends ScheduleAbstractContentController {

    /** The root. */
    @FXML
    private AnchorPane root;

    /** The foot. */
    @FXML
    private HBox foot;

    /** The header. */
    @FXML
    private HBox header;

    /** The h1. */
    @FXML
    private Label h1;

    /** The main. */
    @FXML
    private VBox main;

    /** The h2. */
    @FXML
    private Label h2;

    /** The table. */
    @FXML
    private TableView<Room> table;

   

    @Inject
    private RoomsDataModel model;

    public RoomListController() {
        super();
        setName(getClass().getName());
    }

    @Override
    public Node getRootNode() {
        return this.root;
    }

    /**
     * Initialize.
     * @param <T>
     *            the generic type
     */
    @FXML
    public <T> void initialize() {
        this.h1.textProperty().set("Rooms List");
        this.h2.textProperty().set("");

        // my be set the model

        this.table.setItems(this.model.getRooms());
    }

}
