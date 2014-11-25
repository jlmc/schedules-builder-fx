package org.xine.schedules.builder.fx.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.gui.ContentController;
import org.xine.schedules.builder.fx.gui.xcomponents.Searchablekeyboard;

// TODO: Auto-generated Javadoc
/**
 * The Class RoomsController.
 */
@FXMLController
public class RoomsController extends ContentController {

    /** The root. */
    @FXML
    private AnchorPane root;

    @FXML
    private Searchablekeyboard<String> searchablekeyboard;

    /**
     * Instantiates a new rooms controller.
     */
    public RoomsController() {
        super();
        setName("rooms");
    }

    @FXML
    public void initialize() {
        System.out.println("Ola pessoal");

        this.searchablekeyboard.setSearchableAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                System.out.println("viva sucesso:");

            }
        });

        this.searchablekeyboard.getChoiseTypes().addAll("Nome", "Tipo", "Capacidade");
        this.searchablekeyboard.setPromptText("Put text here..");
    }

    /*
     * (non-Javadoc)
     * @see org.xine.schedules.builder.fx.gui.ContentController#getRootNode()
     */
    @Override
    public Node getRootNode() {
        return this.root;
    }

}
