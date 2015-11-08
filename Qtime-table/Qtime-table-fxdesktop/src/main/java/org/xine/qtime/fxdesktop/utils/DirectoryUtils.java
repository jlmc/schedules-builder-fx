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

import java.io.File;

/**
 * The Class DirectoryUtils.
 */
public final class DirectoryUtils {

	/** The Constant PLUGINS. */
	private static final String PLUGINS = "plugins";

	/** The Constant XINE. */
	private static final String XINE = "xine";

	/**
	 * Gets the working directory.
	 *
	 * @param applicationName
	 *            the application name
	 * @return the working directory
	 */
	public static File getWorkingDirectory(String applicationName) {
		String userHome = System.getProperty("user.home", ".");
		File workingDirectory;

		OperatingSystem os = OperatingSystem.getOS();

		if (os.isWindows()) {
			String applicationData = System.getenv("APPDATA");
			if (applicationData != null) {
				workingDirectory = new File(userHome, "." + applicationName
						+ "/");
			} else {
				workingDirectory = new File(userHome,
						'.' + applicationName + '/');
			}
		} else if (os.isUnix()) {
			workingDirectory = new File(userHome, '.' + applicationName + '/');
		} else if (os.isMac()) {
			workingDirectory = new File(userHome,
					"Library/Application Support/" + applicationName);
		} else {
			workingDirectory = new File(userHome, applicationName + '/');
		}

		if ((!workingDirectory.exists()) && (!workingDirectory.mkdirs())) {
			throw new RuntimeException(
					"The working directory could not be created: "
							+ workingDirectory);
		}
		return workingDirectory;
	}

	/**
	 * Gets the working directory.
	 *
	 * @return the working directory
	 */
	public static File getWorkingDirectory() {
		return getWorkingDirectory(XINE);
	}

	/**
	 * Gets the plugins directory.
	 *
	 * @return the plugins directory
	 */
	public static File getPluginsDirectory() {
		return safeMkDir(new File(getWorkingDirectory(), PLUGINS));
	}

	/**
	 * Safe mk dir.
	 *
	 * @param dir
	 *            the dir
	 * @return the file
	 */
	private static File safeMkDir(File dir) {
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	/**
	 * Instantiates a new directory utils.
	 */
	private DirectoryUtils() {
		super();
	}
}
