package org.xine.schedules.builder.fx.components;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import org.xine.fx.guice.FXMLComponent;
import org.xine.schedules.builder.fx.gui.UiUtils;

@FXMLComponent(resources = "org.xine.schedules.builder.fx.components.ScheduleComponent")
public class ScheduleComponent extends ScheduleAbstractComponent {

    @FXML
    private AnchorPane root;

    @Override
    protected Pane getContent() {
        return this.root;
    }

    @FXML
    public void initialize() {
        UiUtils.setControllerConstrains(this.root);
    }

}
