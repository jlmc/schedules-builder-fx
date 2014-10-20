package org.xine.schedules.builder.fx.components.rooms;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import org.xine.fx.guice.FXMLComponent;
import org.xine.schedules.builder.fx.components.ScheduleAbstractComponent;
import org.xine.schedules.builder.fx.components.SubType;

@FXMLComponent(resources = "org.xine.schedules.builder.fx.components.subjects.RoomsPanel")
public class RoomsPanel extends ScheduleAbstractComponent {

    private static final Map<SubType, String> SUBVIEWS = Collections.unmodifiableMap(new HashMap<SubType, String>() {
        private static final long serialVersionUID = 1L;
        {
            put(SubType.LIST, RoomsViews.LIST_VIEW);
            put(SubType.CREATE, RoomsViews.CREATE_VIEW);
            put(SubType.VIEW, RoomsViews.DETAILS_VIEW);
            put(SubType.EDIT, RoomsViews.EDIT_VIEW);
        }
    });

    /** The root. */
    @FXML
    private AnchorPane root;

    private RoomsDataModel model;

    @Override
    protected Pane getContent() {
        return this.root;
    }

    @Override
    public void onQuit() {
        super.onQuit();
    }

}
