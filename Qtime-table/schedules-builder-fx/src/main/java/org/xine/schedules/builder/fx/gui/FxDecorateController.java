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

import java.util.LinkedList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import org.xine.schedules.builder.fx.utils.OperatingSystem;

// TODO: Auto-generated Javadoc
/**
 * The Class FxDecorateController.
 */
public class FxDecorateController {

    /**
     * The Class ResizeNodeDraggedHandler.
     */
    private final class ResizeNodeDraggedHandler implements EventHandler<MouseEvent> {

        /** The handle. */
        private final Node handle;

        /**
         * The Constructor.
         * @param handle
         *            the handle
         */
        private ResizeNodeDraggedHandler(final Node handle) {
            this.handle = handle;
        }

        /*
         * (non-Javadoc)
         * @see javafx.event.EventHandler#handle(javafx.event.Event)
         */
        @SuppressWarnings("synthetic-access")
        @Override
        public void handle(final MouseEvent e) {
            if (FxDecorateController.this.currentResizeHandle.equals(this.handle.getCursor())) {
                final double dX = e.getScreenX() - FxDecorateController.this.moveStartPoint.getX();
                final double dY = e.getScreenY() - FxDecorateController.this.moveStartPoint.getY();
                final Stage stage = getScene().getStage();
                if (FxDecorateController.this.currentResizeHandle == Cursor.W_RESIZE || FxDecorateController.this.currentResizeHandle == Cursor.NW_RESIZE || FxDecorateController.this.currentResizeHandle == Cursor.SW_RESIZE) {
                    if (setWindowWidth(FxDecorateController.this.startWidth - dX)) {
                        stage.setX(FxDecorateController.this.startX + dX);
                    }
                }
                if (FxDecorateController.this.currentResizeHandle == Cursor.N_RESIZE || FxDecorateController.this.currentResizeHandle == Cursor.NW_RESIZE || FxDecorateController.this.currentResizeHandle == Cursor.NE_RESIZE) {
                    if (setWindowHeight(FxDecorateController.this.startHeight - dY)) {
                        stage.setY(FxDecorateController.this.startY + dY);
                    }
                }
                if (FxDecorateController.this.currentResizeHandle == Cursor.E_RESIZE || FxDecorateController.this.currentResizeHandle == Cursor.SE_RESIZE || FxDecorateController.this.currentResizeHandle == Cursor.NE_RESIZE) {
                    setWindowWidth(FxDecorateController.this.startWidth + dX);
                }
                if (FxDecorateController.this.currentResizeHandle == Cursor.S_RESIZE || FxDecorateController.this.currentResizeHandle == Cursor.SE_RESIZE || FxDecorateController.this.currentResizeHandle == Cursor.SW_RESIZE) {
                    setWindowHeight(FxDecorateController.this.startHeight + dY);
                }
                e.consume();
            }

        }
    }

    /**
     * The Class MoveNodeDraggedEventHandler.
     */
    private final class MoveNodeDraggedEventHandler implements EventHandler<MouseEvent> {

        /** The added. */
        private final Node added;

        /**
         * The Constructor.
         * @param added
         *            the added
         */
        private MoveNodeDraggedEventHandler(final Node added) {
            this.added = added;
        }

        /*
         * (non-Javadoc)
         * @see javafx.event.EventHandler#handle(javafx.event.Event)
         */
        @SuppressWarnings("synthetic-access")
        @Override
        public void handle(final MouseEvent e) {
            if (FxDecorateController.this.currentMoveNode.equals(this.added)) {
                final Point2D delta = new Point2D(e.getScreenX() - FxDecorateController.this.moveStartPoint.getX(), e.getScreenY() - FxDecorateController.this.moveStartPoint.getY());
                double stageX = getScene().getWindow().getX();
                double stageY = getScene().getWindow().getY();

                stageX += delta.getX();
                stageY += delta.getY();

                final Rectangle2D newBounds = new Rectangle2D(stageX, stageY, getScene().getWindow().getWidth(), getScene().getWindow().getHeight());

                // check screen bounds
                for (final Screen screen : Screen.getScreensForRectangle(newBounds)) {
                    // Limit dragging over menu bar
                    if (screen.getVisualBounds().equals(Screen.getPrimary().getVisualBounds()) && OperatingSystem.getOS().isMac() && stageY < screen.getVisualBounds().getMinY()) {
                        stageY = screen.getVisualBounds().getMinY();
                    }
                }

                getScene().getWindow().setX(stageX);
                getScene().getWindow().setY(stageY);
                FxDecorateController.this.moveStartPoint = new Point2D(e.getScreenX(), e.getScreenY());
                e.consume();
            }
        }
    }

    /** The move nodes. */
    @SuppressWarnings("unused")
    private final List<Node> moveNodes = new LinkedList<Node>();

    /** The primary move node. */
    private Node primaryMoveNode = null;

    /** The scene. */
    private final FxDecorateScene scene;

    /** The current move node. */
    private Node currentMoveNode = null;

    /** The move start point. */
    private Point2D moveStartPoint = null;

    /**
     * The Constructor.
     * @param scene
     *            the scene
     */
    FxDecorateController(final FxDecorateScene scene) {
        this.scene = scene;
    }

    /**
     * Adds the move node.
     * @param added
     *            the added
     */
    @SuppressWarnings("synthetic-access")
    public void addMoveNode(final Node added) {
        if (this.primaryMoveNode == null) {
            this.primaryMoveNode = added;
        }
        this.moveNodes.add(added);
        added.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent e) {
                FxDecorateController.this.currentMoveNode = added;
                FxDecorateController.this.moveStartPoint = new Point2D(e.getScreenX(), e.getScreenY());
                e.consume();
            }
        });

        added.setOnMouseDragged(new MoveNodeDraggedEventHandler(added));

        added.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent e) {
                if (FxDecorateController.this.currentMoveNode.equals(added)) {
                    FxDecorateController.this.currentMoveNode = null;
                    // checkScreenEdges();
                    e.consume();
                }
            }
        });
    }

    /**
     * Removes the move node.
     * @param removed
     *            the removed
     */
    public void removeMoveNode(final Node removed) {
        this.moveNodes.remove(removed);
        removed.setOnMouseMoved(null);
        removed.setOnMousePressed(null);
        removed.setOnMouseReleased(null);
    }

    /**
     * Gets the scene.
     * @return the scene
     */
    public FxDecorateScene getScene() {
        return this.scene;
    }

    /**
     * Center on screen.
     */
    public void centerOnScreen() {
        final Screen primary = Screen.getPrimary();
        final Rectangle2D bounds = primary.getVisualBounds();
        final double width = getScene().getStage().getWidth();
        final double height = getScene().getStage().getHeight();
        final double x = bounds.getMinX() + (bounds.getWidth() - width) / 2d;
        final double y = bounds.getMinY() + (bounds.getHeight() - height) / 2d;

        getScene().getWindow().setX(x);
        getScene().getWindow().setY(y);
    }

    /**
     * checks if the primary move node is off screen and if so, resets the
     * window so it is visible again.
     */
    @SuppressWarnings({"unused", "boxing" })
    public void checkScreenEdges() {
        if (this.primaryMoveNode == null) {
            return;
        }

        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;

        for (final Screen screen : Screen.getScreens()) {
            minX = Math.min(minX, screen.getVisualBounds().getMinX());
            minY = Math.min(minY, screen.getVisualBounds().getMinY());

            maxX = Math.max(maxX, screen.getVisualBounds().getMaxX());
            maxY = Math.max(maxY, screen.getVisualBounds().getMaxY());
        }

        final Rectangle2D bounds = getScreenBounds(this.primaryMoveNode);
        Rectangle2D valid = bounds;
        if (!isValidBounds(bounds)) {
            if (bounds.getMinY() < minY || bounds.getMaxY() > maxY) {
                int dir = 1;
                if (bounds.getMaxY() > maxY) {
                    dir = -1;
                }
                for (double y = minX; y <= maxY && y >= minY; y += dir) {
                    final Rectangle2D test = cloneRect(bounds, null, y, null, null);
                    if (isValidBounds(test)) {
                        valid = test;
                    }
                }
            }
        }
        // TODO handle when new bounds were found
    }

    /** The start height. */
    private double startX, startY, startWidth, startHeight;

    /** The current resize handle. */
    private Cursor currentResizeHandle = null;

    /**
     * Sets the resizeable handle.
     * @param handle
     *            the resizeable handle
     */
    @SuppressWarnings("synthetic-access")
    void setResizeableHandle(final Node handle) {
        handle.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent e) {
                if (FxDecorateController.this.currentResizeHandle == null) {
                    FxDecorateController.this.startX = getScene().getWindow().getX();
                    FxDecorateController.this.startY = getScene().getWindow().getY();
                    FxDecorateController.this.startWidth = getScene().getWindow().getWidth();
                    FxDecorateController.this.startHeight = getScene().getWindow().getHeight();
                    FxDecorateController.this.moveStartPoint = new Point2D(e.getScreenX(), e.getScreenY());
                    FxDecorateController.this.currentResizeHandle = handle.getCursor();
                    e.consume();
                }
            }
        });
        handle.setOnMouseDragged(new ResizeNodeDraggedHandler(handle));
        handle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent e) {
                FxDecorateController.this.currentResizeHandle = null;
                FxDecorateController.this.moveStartPoint = null;
                e.consume();
            }
        });
    }

    /**
     * Sets the window height.
     * @param height
     *            the window height
     * @return true, if sets the window height
     */
    private boolean setWindowHeight(final double height) {
        if (height > getScene().getStage().getMaxWidth()) {
            return false;
        }
        if (height < getScene().getStage().getMinHeight()) {
            return false;
        }
        getScene().getStage().setHeight(height);
        return true;
    }

    /**
     * Sets the window width.
     * @param width
     *            the window width
     * @return true, if sets the window width
     */
    private boolean setWindowWidth(final double width) {
        if (width > getScene().getStage().getMaxWidth()) {
            return false;
        }
        if (width < getScene().getStage().getMinWidth()) {
            return false;
        }
        getScene().getStage().setWidth(width);
        return true;
    }

    /**
     * Returns if the given rectangle has a valid position for a primary move
     * node.<br/>
     * Conditions are:
     * <ul>
     * <li>at least 10 vertical pixels must be visible</li>
     * <li>the whole height must be visible</li>
     * </ul>
     * @param bounds
     *            the rectangle that should be checked
     * @return true, if checks if is valid bounds
     */
    @SuppressWarnings("static-method")
    public boolean isValidBounds(final Rectangle2D bounds) {
        final ObservableList<Screen> screens = Screen.getScreensForRectangle(bounds);
        if (screens.size() == 0) {
            return false;
        }
        for (final Screen screen : Screen.getScreensForRectangle(bounds)) {
            final Rectangle2D vis = screen.getVisualBounds();
            if (bounds.getMinY() < vis.getMinY() || bounds.getMaxY() > vis.getMaxY()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Manually sets the primary move node.<br/>
     * It will be made sure that this node is always available to be dragged so
     * the user can't move it off screen and have no possibility to drag it.
     * @param primaryMoveNode
     *            the primary move node
     */
    public void setPrimaryMoveNode(final Node primaryMoveNode) {
        this.primaryMoveNode = primaryMoveNode;
    }

    /**
     * Gets the primary move node.
     * @return the primary move node
     */
    public Node getPrimaryMoveNode() {
        return this.primaryMoveNode;
    }

    /**
     * Gets the screen bounds.
     * @param node
     *            the node
     * @return the screen bounds
     */
    private static Rectangle2D getScreenBounds(final Node node) {
        double windowX = node.getScene().getWindow().getX();
        double windowY = node.getScene().getWindow().getY();

        final Point2D scenepos = node.localToScene(0, 0);

        windowX += scenepos.getX();
        windowY += scenepos.getY();

        return new Rectangle2D(windowX, windowY, node.getLayoutBounds().getWidth(), node.getLayoutBounds().getHeight());
    }

    /**
     * Clone rect.
     * @param c
     *            the c
     * @param x
     *            the x
     * @param y
     *            the y
     * @param width
     *            the width
     * @param height
     *            the height
     * @return the rectangle2 d
     */
    @SuppressWarnings("boxing")
    private static Rectangle2D cloneRect(final Rectangle2D c, final Double x, final Double y, final Double width, final Double height) {
        return new Rectangle2D(x != null ? x : c.getMinX(), y != null ? y : c.getMinY(), width != null ? width : c.getWidth(), height != null ? height : c.getHeight());
    }
}
