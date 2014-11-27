package org.xine.qtime.fxdesktop.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;

import org.xine.fx.guice.FXMLController;

@FXMLController
public class StartUpController extends ContentController {
    /** The root. */
    @FXML
    private AnchorPane root;

    /** The play button. */
    @FXML
    private Button playButton;

    /** The progress. */
    @FXML
    private ProgressIndicator progress;

    /**
     * Instantiates a new start up controller.
     */
    public StartUpController() {
        super();
        setName("StartUp");
    }

    /*
     * (non-Javadoc)
     * @see org.xine.schedules.builder.fx.gui.ContentController#getRootNode()
     */
    @Override
    public Node getRootNode() {
        return this.root;
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        this.progress.setVisible(false);
    }

    /**
     * On play clicked.
     * @param actionEvent
     *            the action event
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @FXML
    public void onPlayClicked(final ActionEvent actionEvent) throws IOException {
        System.out.println("testin events");
        this.progress.setVisible(true);
    }

}
