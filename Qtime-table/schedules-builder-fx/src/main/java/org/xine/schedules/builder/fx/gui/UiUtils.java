package org.xine.schedules.builder.fx.gui;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * The Class UiUtils.
 */
public class UiUtils {

    /**
     * Sets the controller constrains.
     * @param controller
     *            the new controller constrains
     */
    public static void setControllerConstrains(final ContentController controller) {
        AnchorPane.setTopAnchor(controller.getRootNode(), Double.valueOf(0d));
        AnchorPane.setLeftAnchor(controller.getRootNode(), Double.valueOf(0d));
        AnchorPane.setRightAnchor(controller.getRootNode(), Double.valueOf(0d));
        AnchorPane.setBottomAnchor(controller.getRootNode(), Double.valueOf(0d));
    }

    /**
     * Sets the controller constrains.
     * @param node
     *            the new controller constrains
     */
    public static void setControllerConstrains(final Node node) {
        AnchorPane.setTopAnchor(node, Double.valueOf(0d));
        AnchorPane.setLeftAnchor(node, Double.valueOf(0d));
        AnchorPane.setRightAnchor(node, Double.valueOf(0d));
        AnchorPane.setBottomAnchor(node, Double.valueOf(0d));
    }
}
