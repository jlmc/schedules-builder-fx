/*
 * Copyright (c) 2012 Spout LLC <http://www.spout.org>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */
/* 
* Copyright (c) 2015 Qxine <https://github.com/jlmc>
* All Rights Reserved, unless otherwise granted permission.
*
* You may use and modify for private use, fork the official repository
* for contribution purposes, contribute code, and reuse your own code.
*/
package org.xine.qtime.fxdesktop.controllers;

import org.xine.fx.guice.FXMLController;

import java.io.IOException;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;

/**
 * The Class StartUpController.
 */
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

    /** The counter. */
    @SuppressWarnings("unused")
    private Task<Void> counter;

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
