/* 
* Copyright (c) 2015 Qxine <https://github.com/jlmc>
* All Rights Reserved, unless otherwise granted permission.
*
* You may use and modify for private use, fork the official repository
* for contribution purposes, contribute code, and reuse your own code.
*/

package org.xine.qtime.fxdesktop.backoffice.subjects;

import org.xine.qtime.fxdesktop.controllers.StateController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * The Class SubjectListController.
 */
public class SubjectListController extends StateController {

    /**
     * The resources.
     * ResourceBundle that was given to the FXMLLoader.
     */
    @FXML
    private ResourceBundle resources;

    /**
     * The location.
     * URL location of the FXML file that was given to the FXMLLoader.
     */
    @FXML
    private URL location;

    /** The name column. */
    @FXML
    private TableColumn<?, ?> nameColumn;

    /** The actions column. */
    @FXML
    private TableColumn<?, ?> actionsColumn;

    /** The root. */
    @FXML
    private AnchorPane root;

    /** The create button. */
    @FXML
    private Button createButton;

    /** The title. */
    @FXML
    private Label title;

    /** The table. */
    @FXML
    private TableView<?> table;

    /** The description column. */
    @FXML
    private TableColumn<?, ?> descriptionColumn;

    /** The glass pane. */
    @FXML
    private BorderPane glassPane;

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.controllers.ContentController#getRootNode()
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

        assert this.nameColumn != null : "fx:id=\"nameColumn\" was not injected: check your FXML file 'subjectListView.fxml'.";
        assert this.actionsColumn != null : "fx:id=\"actionsColumn\" was not injected: check your FXML file 'subjectListView.fxml'.";
        assert this.root != null : "fx:id=\"root\" was not injected: check your FXML file 'subjectListView.fxml'.";
        assert this.createButton != null : "fx:id=\"createButton\" was not injected: check your FXML file 'subjectListView.fxml'.";
        assert this.title != null : "fx:id=\"title\" was not injected: check your FXML file 'subjectListView.fxml'.";
        assert this.table != null : "fx:id=\"table\" was not injected: check your FXML file 'subjectListView.fxml'.";
        assert this.descriptionColumn != null : "fx:id=\"descriptionColumn\" was not injected: check your FXML file 'subjectListView.fxml'.";
        assert this.glassPane != null : "fx:id=\"glassPane\" was not injected: check your FXML file 'subjectListView.fxml'.";

        this.createButton.setOnAction(e -> getMachineStatesController().setActiveController(
                getMachineStatesController().getCreateController()));
    }

}
