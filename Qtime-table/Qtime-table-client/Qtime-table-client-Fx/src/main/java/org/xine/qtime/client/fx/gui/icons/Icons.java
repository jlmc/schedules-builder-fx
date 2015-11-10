package org.xine.qtime.client.fx.gui.icons;

import org.xine.qtime.client.fx.utils.ImageUtils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The Class IconUtil.
 */
public enum Icons {


	/** The user. */
	USER("/images/user.png"),

	/** The e plan. */
	E_PLAN("/images/e-plan.png");

	/** The path. */
	private final String path;

	/**
	 * Instantiates a new icon.
	 *
	 * @param path
	 *            the path
	 */
	Icons(final String path) {
		this.path = path;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	private String getPath() {
		return this.path;
	}

	/**
	 * Gets the icon.
	 *
	 * @param icon
	 *            the icon info
	 * @return the icon
	 */
	public static Image getIcon(final Icons icon) {
		return ImageUtils.getFXImageSafely(icon.getPath());
	}

	/**
	 * Gets the icon.
	 *
	 * @param icon the icon
	 * @param width the width
	 * @param height the height
	 * @return the icon
	 */
	public static ImageView getIcon(final Icons icon, final int width,
			final int height){
		return ImageUtils.getIcon(icon.getPath(), width, height);
	}
}
