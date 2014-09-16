/*
 * This file is part of FxDecorate.
 *
 * Copyright (c) 2013 Moritz Schmale <http://www.narrowtux.com/>
 * Modifications copyright (c) 2013 Spout LLC <http://www.spout.org/>
 * FxDecorate is licensed under the GNU Lesser General Public License.
 *
 * FxDecorate is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FxDecorate is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.xine.schedules.builder.fx.gui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class FxDecorateScene.
 */
public class FxDecorateScene extends Scene {

    /** The content. */
    private final Node content;

    /** The edge size. */
    private final SimpleIntegerProperty edgeSize = new SimpleIntegerProperty(1);

    /** The resize south. */
    private final Rectangle resizeWest, resizeEast, resizeNorth, resizeSouth;

    /** The resize south west. */
    private final Rectangle resizeNorthEast, resizeNorthWest, resizeSouthEast, resizeSouthWest;

    /** The pane. */
    private final AnchorPane pane;

    /** The stage. */
    private final Stage stage;

    /** The controller. */
    private final FxDecorateController controller;

    /**
     * Instantiates a new fx decorate scene.
     * @param content
     *            the content
     * @param stage
     *            the stage
     */
    @SuppressWarnings("boxing")
    public FxDecorateScene(final Node content, final Stage stage) {
        super(new AnchorPane());
        this.controller = new FxDecorateController(this);
        this.pane = (AnchorPane) getRoot();
        this.stage = stage;

        final Color edges = Color.TRANSPARENT;

        // Align content
        this.content = content;
        this.pane.getChildren().add(content);
        AnchorPane.setLeftAnchor(content, 0D);
        AnchorPane.setTopAnchor(content, 0d);
        AnchorPane.setRightAnchor(content, 0d);
        AnchorPane.setBottomAnchor(content, 0d);

        // Align west resize handle
        this.resizeWest = new Rectangle();
        this.resizeWest.setCursor(Cursor.W_RESIZE);
        this.resizeWest.setFill(edges);
        this.pane.getChildren().add(this.resizeWest);
        this.resizeWest.widthProperty().bind(this.edgeSize);
        this.resizeWest.heightProperty().bind(heightProperty());
        AnchorPane.setLeftAnchor(this.resizeWest, 0d);
        AnchorPane.setTopAnchor(this.resizeWest, 0d);
        this.controller.setResizeableHandle(this.resizeWest);

        // Align top border
        this.resizeNorth = new Rectangle();
        this.resizeNorth.setCursor(Cursor.N_RESIZE);
        this.resizeNorth.setFill(edges);
        this.pane.getChildren().add(this.resizeNorth);
        this.resizeNorth.widthProperty().bind(widthProperty());
        this.resizeNorth.heightProperty().bind(this.edgeSize);
        AnchorPane.setLeftAnchor(this.resizeNorth, 0d);
        AnchorPane.setTopAnchor(this.resizeNorth, 0d);
        this.controller.setResizeableHandle(this.resizeNorth);

        // Align right border
        this.resizeEast = new Rectangle();
        this.resizeEast.setCursor(Cursor.E_RESIZE);
        this.resizeEast.setFill(edges);
        this.pane.getChildren().add(this.resizeEast);
        this.resizeEast.widthProperty().bind(this.edgeSize);
        this.resizeEast.heightProperty().bind(heightProperty());
        AnchorPane.setTopAnchor(this.resizeEast, 0d);
        AnchorPane.setRightAnchor(this.resizeEast, 0d);
        this.controller.setResizeableHandle(this.resizeEast);

        // Align bottom border
        this.resizeSouth = new Rectangle();
        this.resizeSouth.setCursor(Cursor.S_RESIZE);
        this.resizeSouth.setFill(edges);
        this.pane.getChildren().add(this.resizeSouth);
        this.resizeSouth.widthProperty().bind(widthProperty());
        this.resizeSouth.heightProperty().bind(this.edgeSize);
        AnchorPane.setLeftAnchor(this.resizeSouth, 0d);
        AnchorPane.setBottomAnchor(this.resizeSouth, 0d);
        this.controller.setResizeableHandle(this.resizeSouth);

        final Color corners = Color.TRANSPARENT;

        // Align north-west resize handle
        this.resizeNorthWest = new Rectangle();
        this.resizeNorthWest.setCursor(Cursor.NW_RESIZE);
        this.resizeNorthWest.setFill(corners);
        this.pane.getChildren().add(this.resizeNorthWest);
        this.resizeNorthWest.widthProperty().bind(this.edgeSize);
        this.resizeNorthWest.heightProperty().bind(this.edgeSize);
        AnchorPane.setLeftAnchor(this.resizeNorthWest, 0d);
        AnchorPane.setTopAnchor(this.resizeNorthWest, 0d);
        this.controller.setResizeableHandle(this.resizeNorthWest);

        // Align north-east resize handle
        this.resizeNorthEast = new Rectangle();
        this.resizeNorthEast.setCursor(Cursor.NE_RESIZE);
        this.resizeNorthEast.setFill(corners);
        this.pane.getChildren().add(this.resizeNorthEast);
        this.resizeNorthEast.widthProperty().bind(this.edgeSize);
        this.resizeNorthEast.heightProperty().bind(this.edgeSize);
        AnchorPane.setRightAnchor(this.resizeNorthEast, 0d);
        AnchorPane.setTopAnchor(this.resizeNorthEast, 0d);
        this.controller.setResizeableHandle(this.resizeNorthEast);

        // Align south-east resize handle
        this.resizeSouthEast = new Rectangle();
        this.resizeSouthEast.setCursor(Cursor.SE_RESIZE);
        this.resizeSouthEast.setFill(corners);
        this.pane.getChildren().add(this.resizeSouthEast);
        this.resizeSouthEast.widthProperty().bind(this.edgeSize);
        this.resizeSouthEast.heightProperty().bind(this.edgeSize);
        AnchorPane.setRightAnchor(this.resizeSouthEast, 0d);
        AnchorPane.setBottomAnchor(this.resizeSouthEast, 0d);
        this.controller.setResizeableHandle(this.resizeSouthEast);

        // Align south-west resize handle
        this.resizeSouthWest = new Rectangle();
        this.resizeSouthWest.setCursor(Cursor.SW_RESIZE);
        this.pane.getChildren().add(this.resizeSouthWest);
        this.resizeSouthWest.setFill(corners);
        this.resizeSouthWest.widthProperty().bind(this.edgeSize);
        this.resizeSouthWest.heightProperty().bind(this.edgeSize);
        AnchorPane.setLeftAnchor(this.resizeSouthWest, 0d);
        AnchorPane.setBottomAnchor(this.resizeSouthWest, 0d);
        this.controller.setResizeableHandle(this.resizeSouthWest);
    }

    /**
     * Gets the edge size.
     * @return the edge size
     */
    public int getEdgeSize() {
        return this.edgeSize.get();
    }

    /**
     * Sets the edge size.
     * @param size
     *            the new edge size
     */
    public void setEdgeSize(final int size) {
        this.edgeSize.set(size);
    }

    /**
     * Edge size property.
     * @return the integer property
     */
    public IntegerProperty edgeSizeProperty() {
        return this.edgeSize;
    }

    /**
     * Gets the root node.
     * @return the root node
     */
    public Node getRootNode() {
        return this.content;
    }

    /**
     * Gets the controller.
     * @return the controller
     */
    public FxDecorateController getController() {
        return this.controller;
    }

    /**
     * Gets the stage.
     * @return the stage
     */
    public Stage getStage() {
        return this.stage;
    }
}
