/* 
* Copyright (c) 2015 Qxine <https://github.com/jlmc>
* All Rights Reserved, unless otherwise granted permission.
*
* You may use and modify for private use, fork the official repository
* for contribution purposes, contribute code, and reuse your own code.
*/
package org.xine.qtime.fxdesktop.backoffice;

import org.xine.qtime.fxdesktop.controllers.ContentController;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * The Class PersonsController.
 */
public class PersonsController extends ContentController {

    /** The identify. */
    @FXML
    private Label identify;

    /** The root. */
    @FXML
    private AnchorPane root;

    /** The content. */
    @FXML
    private StackPane content;

    /**
     * Instantiates a new persons controller.
     */
    public PersonsController() {
        super();
        setName("Pessoas");
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.controllers.ContentController#getRootNode()
     */
    @Override
    public Node getRootNode() {
        return this.root;
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.controllers.ContentController#onActivate()
     */
    @Override
    public void onActivate() {
        System.out.println("pessoas ON");
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.controllers.ContentController#onDeactivate()
     */
    @Override
    public void onDeactivate() {
        System.out.println("pessoas OFF");
    }
}
