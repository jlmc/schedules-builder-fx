package org.xine.qtime.fxdesktop.utils;

/**
 * The Enum OperatingSystem.
 */
public enum OperatingSystem {

    /** The unix. */
    UNIX("Unix"),
    /** The linux. */
    LINUX("Linux"),
    /** The solaris. */
    SOLARIS("Solaris"),
    /** The windows xp. */
    WINDOWS_XP("Windows XP"),
    /** The windows vista. */
    WINDOWS_VISTA("Windows Vista"),
    /** The WINDOW s_7. */
    WINDOWS_7("Windows 7"),
    /** The WINDOW s_8. */
    WINDOWS_8("Windows 8"),
    /** The windows unknown. */
    WINDOWS_UNKNOWN("Windows"),
    /** The mac osx. */
    MAC_OSX("Mac OS X"),
    /** The mac. */
    MAC("Mac"),
    /** The unknown. */
    UNKNOWN("");

    /** The identifier. */
    private final String identifier;

    /**
     * The Constructor.
     * @param system
     *            the system
     */
    OperatingSystem(final String system) {
        this.identifier = system.toLowerCase();
    }

    /**
     * Checks if is unix.
     * @return true, if checks if is unix
     */
    public boolean isUnix() {
        return this == UNIX || this == LINUX || this == SOLARIS;
    }

    /**
     * Checks if is mac.
     * @return true, if checks if is mac
     */
    public boolean isMac() {
        return this == MAC_OSX || this == MAC;
    }

    /**
     * Checks if is windows.
     * @return true, if checks if is windows
     */
    public boolean isWindows() {
        return this == WINDOWS_XP || this == WINDOWS_VISTA || this == WINDOWS_7
                || this == WINDOWS_8 || this == WINDOWS_UNKNOWN;
    }

    /**
     * Gets the os.
     * @return the os
     */
    public static OperatingSystem getOS() {
        OperatingSystem best = UNKNOWN;
        final String os = System.getProperty("os.name").toLowerCase();
        for (final OperatingSystem system : values()) {
            if (os.contains(system.identifier)) {
                if (system.identifier.length() > best.identifier.length()) {
                    best = system;
                }
            }
        }
        return best;
    }
}
