package org.xine.qtime.client.fx.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public final class ImageUtils {

	private ImageUtils() {
		super();
	}

	public static BufferedImage scaleImage(final BufferedImage img, final int width, final int height) {
		int widthh = width;
		int heightt = height;
		final int imgWidth = img.getWidth();
		final int imgHeight = img.getHeight();
		if (imgWidth * heightt < imgHeight * widthh) {
			widthh = imgWidth * heightt / imgHeight;
		} else {
			heightt = imgHeight * widthh / imgWidth;
		}
		final BufferedImage newImage = new BufferedImage(widthh, heightt, BufferedImage.TYPE_INT_ARGB);
		final Graphics2D g = newImage.createGraphics();
		try {
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.drawImage(img, 0, 0, widthh, heightt, null);
		} finally {
			g.dispose();
		}
		return newImage;
	}

	public static BufferedImage getImageSafely(final String path) {
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

	public static Image getFXImageSafely(final String path) {
		InputStream stream = null;
		try {
			stream = ResourceUtils.getResourceAsStream(path);
			return new Image(stream);
		} finally {
			IOUtils.closeQuietly(stream);
		}
	}

	public static ImageView getIcon(String path, int width, int height) {
		final java.awt.image.BufferedImage bi2 = ImageUtils.scaleImage(ImageUtils.getImageSafely(path), width, height);

		WritableImage wr = null;
		if (bi2 != null) {
			wr = new WritableImage(bi2.getWidth(), bi2.getHeight());
			final PixelWriter pw = wr.getPixelWriter();
			for (int x = 0; x < bi2.getWidth(); x++) {
				for (int y = 0; y < bi2.getHeight(); y++) {
					pw.setArgb(x, y, bi2.getRGB(x, y));
				}
			}
		}

		return new ImageView(wr);
	}
}
