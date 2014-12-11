package org.xine.qtime.fxdesktop.backoffice;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import org.xine.qtime.fxdesktop.controllers.ContentController;

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
