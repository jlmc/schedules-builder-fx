package org.xine.qtime.fxdesktop.controllers;

import org.xine.fx.guice.FXMLController;
import org.xine.qtime.fxdesktop.gui.Views;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * The Class BackOfficeController.
 */
@FXMLController
public class BackOfficeController extends ContextController {

    /** The resources. */
    @FXML
    private ResourceBundle resources;

    /** The location. */
    @FXML
    private URL location;

    /** The root. */
    @FXML
    private AnchorPane root;

    /** The nav. */
    @FXML
    private VBox nav;

    /** The component. */
    @FXML
    private AnchorPane component;

    /** The nav items. */
    @FXML
    private VBox navItems;

    /**
     * Instantiates a new back office controller.
     */
    public BackOfficeController() {
        super();
        super.setName("BackOffice");
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.controllers.ContentController#getRootNode()
     */
    @Override
    public Node getRootNode() {
        return this.root;
    }

    /** The subviews. */
    private final List<String> subviews = new LinkedList<String>() {
        private static final long serialVersionUID = 1L;
        {
            add(Views.BACKOFFICE_SUBJECT);
            add(Views.BACKOFFICE_PERSONS);
        }
    };

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        // TODO: thing in other way of loading the subviews using a task
        super.loadsubviews(this.subviews, this.resources);

    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.controllers.ContextController#getComponent()
     */
    @Override
    public Pane getComponent() {
        return this.component;
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.controllers.ContextController#getnavItems()
     */
    @Override
    public Pane getnavItems() {
        return this.navItems;
    }

}
