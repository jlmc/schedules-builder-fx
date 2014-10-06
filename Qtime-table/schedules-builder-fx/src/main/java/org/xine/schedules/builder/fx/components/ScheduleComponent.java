package org.xine.schedules.builder.fx.components;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import org.xine.fx.guice.FXMLComponent;
import org.xine.schedules.builder.fx.gui.UiUtils;

/**
 * The Class ScheduleComponent.
 */
@FXMLComponent(resources = "org.xine.schedules.builder.fx.components.ScheduleComponent")
public class ScheduleComponent extends ScheduleAbstractComponent {

    /** The root. */
    @FXML
    private AnchorPane root;

    /*
     * (non-Javadoc)
     * @see org.xine.schedules.builder.fx.components.ScheduleAbstractComponent#getContent()
     */
    @Override
    protected Pane getContent() {
        return this.root;
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        UiUtils.setControllerConstrains(this.root);
    }

}
