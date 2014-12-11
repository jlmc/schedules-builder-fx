package org.xine.qtime.fxdesktop.backoffice;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import org.xine.fx.guice.FXMLController;
import org.xine.qtime.fxdesktop.controllers.ContentController;

@FXMLController
public class SubjectsController extends ContentController {

    @FXML
    private Label identify;

    @FXML
    private AnchorPane root;

    @FXML
    private StackPane content;

    public SubjectsController() { // org.xine.qtime.fxdesktop.backoffice.SubjectsController
        super();
        setName("Disciplinas");
    }

    @Override
    public Node getRootNode() {
        return this.root;
    }

    @Override
    public void onActivate() {
        System.out.println("disciplina ON");
    }

    @Override
    public void onDeactivate() {
        System.out.println("disciplinas OFF");
    }

}
