package org.xine.schedules.builder.fx.components.subjects;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import org.xine.fx.guice.FXMLComponent;
import org.xine.schedules.builder.fx.components.ScheduleAbstractComponent;
import org.xine.schedules.builder.fx.components.SubType;

/**
 * The Class SubjectsPane.
 */
@FXMLComponent(resources = "org.xine.schedules.builder.fx.components.AbstractPane")
public class SubjectsPane extends ScheduleAbstractComponent {

    /** The root. */
    @FXML
    private AnchorPane root;

    /** The Constant subviews. */
    private static final Map<SubType, String> SUBVIEWS = Collections.unmodifiableMap(new HashMap<SubType, String>() {
        private static final long serialVersionUID = 1L;
        {
            put(SubType.LIST, SubjectsViews.LIST_VIEW);
            put(SubType.CREATE, SubjectsViews.CREATE_VIEW);
            put(SubType.CREATE, SubjectsViews.CREATE_VIEW);

        }

    });

    /**
     * Gets the content.
     * @return the content
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

        loadSubControllers(SUBVIEWS);

        activateController(SubType.LIST);
    }

}
