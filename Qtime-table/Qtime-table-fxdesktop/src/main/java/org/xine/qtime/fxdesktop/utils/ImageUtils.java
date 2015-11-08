/*
 * Copyright (c) 2012 Spout LLC <http://www.spout.org>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */
/*
 * Copyright (c) 2015 Qxine <https://github.com/jlmc>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */
package org.xine.qtime.fxdesktop.utils;

import org.apache.commons.io.IOUtils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;

/**
 * The Class ImageUtils.
 */
public final class ImageUtils {

    /**
     * Scale image.
     * @param img
     *            the img
     * @param width
     *            the width
     * @param height
     *            the height
     * @return the buffered image
     */
    public static BufferedImage scaleImage(final BufferedImage img, final int width,
            final int height) {
        int widthh = width;
        int heightt = height;
        final int imgWidth = img.getWidth();
        final int imgHeight = img.getHeight();
        if (imgWidth * heightt < imgHeight * widthh) {
            widthh = imgWidth * heightt / imgHeight;
        } else {
            heightt = imgHeight * widthh / imgWidth;
        }
        final BufferedImage newImage = new BufferedImage(widthh, heightt,
                BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g = newImage.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(img, 0, 0, widthh, heightt, null);
        } finally {
            g.dispose();
        }
        return newImage;
    }

    /**
     * Gets the image safely.
     * @param path
     *            the path
     * @return the image safely
     */
    public static BufferedImage getImageSafely(final String path) {
        @SuppressWarnings("resource")
        InputStream stream = null;
        try {
            stream = ResourceUtils.getResourceAsStream(path);
            return ImageIO.read(stream);
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(stream);
        }
        return new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * Gets the FX image safely.
     * @param path
     *            the path
     * @return the FX image safely
     */
    public static Image getFXImageSafely(final String path) {
        @SuppressWarnings("resource")
        InputStream stream = null;
        try {
            stream = ResourceUtils.getResourceAsStream(path);
            return new Image(stream);
        } finally {
            IOUtils.closeQuietly(stream);
        }
    }

    /**
     * Instantiates a new image utils.
     */
    private ImageUtils() {
        super();
    }
}
