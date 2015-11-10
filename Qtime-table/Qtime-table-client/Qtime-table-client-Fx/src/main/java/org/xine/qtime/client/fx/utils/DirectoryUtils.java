package org.xine.qtime.client.fx.utils;

import java.io.File;

public final class DirectoryUtils {

	private static final String PLUGINS = "plugins";
	private static final String XINE = "xine";

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

	public static File getWorkingDirectory() {
		return getWorkingDirectory(XINE);
	}

	public static File getPluginsDirectory() {
		return safeMkDir(new File(getWorkingDirectory(), PLUGINS));
	}

	private static File safeMkDir(File dir) {
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	private DirectoryUtils() {
		super();
	}
}
