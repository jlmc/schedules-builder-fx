package org.xine.qtime.fxdesktop.backoffice;

import org.xine.fx.guice.FXMLController;
import org.xine.qtime.entities.Subject;
import org.xine.qtime.fxdesktop.controllers.MachineStatesController;

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * The Class SubjectsController.
 */
@FXMLController
public class SubjectsController extends MachineStatesController<Subject> implements Initializable {

    /** The identify. */
    @FXML
    private Label identify;

    /** The root. */
    @FXML
    private AnchorPane root;

    /** The views. */
    private final Map<State, String> views = Collections
            .unmodifiableMap(new HashMap<State, String>() {
                private static final long serialVersionUID = 1L;
                {
                    put(State.LIST, "/views/backoffice/subjects/subjectListView.fxml");
                    put(State.CREATE, "/views/backoffice/subjects/subjectCreateView.fxml");
                    put(State.EDIT, "/views/backoffice/subjects/subjectEditView.fxml");
                }

            });

    // @FXML
    // private StackPane content;

    /**
     * Instantiates a new subjects controller.
     */
    public SubjectsController() { // org.xine.qtime.fxdesktop.backoffice.SubjectsController
        super();
        setName("Disciplinas");
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.controllers.MachineStatesController#getRootNode()
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
        // Nothing
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.controllers.ContentController#onDeactivate()
     */
    @Override
    public void onDeactivate() {
        // Nothing
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.controllers.MachineStatesController#getContent()
     */
    @Override
    public Pane getContent() {
        return this.root;
    }

    /*
     * (non-Javadoc)
     * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

        super.loadStates(this.views, location, resources);
        if (getEditController() != null && getListController() != null) {
            getEditController().selectedProperty().bind(getListController().selectedProperty());
        }

    }

}
