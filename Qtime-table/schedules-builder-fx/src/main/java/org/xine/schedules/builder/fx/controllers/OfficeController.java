package org.xine.schedules.builder.fx.controllers;

import java.util.LinkedHashSet;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.gui.CompositeContentController;
import org.xine.schedules.builder.fx.gui.Views;

//@FXMLComponent

/**
 * The Class OfficeController.
 */
@FXMLController
public class OfficeController extends CompositeContentController {

    /** The root. */
    @FXML
    private AnchorPane root;

    /** The navigation background. */
    @FXML
    private Rectangle navigationBackground;

    /** The navigation v box. */
    @FXML
    private VBox navigationVBox;

    /** The menu tree view. */
    @SuppressWarnings("rawtypes")
    @FXML
    private TreeView menuTreeView;

    /** The container. */
    @FXML
    private AnchorPane container;

    /** The office subviews. */
    private final Set<String> officeSubviews = new LinkedHashSet<String>() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        {
            add(Views.SUBJECTS_VIEW);
            add(Views.ROOMS_VIEW);
        }
    };

    // @Inject
    // private GuiceFXMLLoader fxmlLoader;

    /**
     * Instantiates a new office controller.
     */
    public OfficeController() {
        super();
        setName("Office");
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
        // TODO missing set of sub views
        setSubViews(this.officeSubviews.toArray(new String[this.officeSubviews.size()]));

       // buttonBar.getStyleClass().setAll("segmented-button-bar");
        this.navigationVBox.getStyleClass().setAll("segmented-button-bar");
        //this.navigationVBox.heightProperty().bind(this.root.heightProperty());
        
        // TODO missing loadSubcontrolers Call
        loadSubControllers();

        //this.navigationBackground.getStyleClass().setAll("segmented-button-bar");
        //this.navigationBackground.heightProperty().bind(this.root.heightProperty());
    }

    /*
     * (non-Javadoc)
     * @see org.xine.schedules.builder.fx.gui.CompositeContentController#getContent()
     */
    @Override
    protected Pane getContent() {
        return this.container;
    }

    /*
     * (non-Javadoc)
     * @see org.xine.schedules.builder.fx.gui.CompositeContentController#getNavigationContent()
     */
    @Override
    protected Pane getNavigationContent() {
        return this.navigationVBox;
    }

    /*
     * (non-Javadoc)
     * @see org.xine.schedules.builder.fx.gui.CompositeContentController#setNavButtonProperties(javafx.scene.control.Button)
     */
    @Override
    protected void setNavButtonProperties(final Button navButton) {
        // Auto-generated method stub
    }

}
