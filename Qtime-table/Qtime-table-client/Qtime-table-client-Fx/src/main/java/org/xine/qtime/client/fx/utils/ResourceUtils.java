package org.xine.qtime.client.fx.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResourceUtils {

	public static final String RESOURCE_PATH = "/org/xine/qtime/client/fx/resources/";

    /**
     * Gets the resource as stream.
     * @param path
     *            the path
     * @return the resource as stream
     */
    public static InputStream getResourceAsStream(final String path) {
        InputStream stream = ResourceUtils.class.getResourceAsStream(RESOURCE_PATH + path);
        if (stream == null) {
            // Eclipse path
            final File resource = new File(".\\src\\main\\resources\\" + path);
            if (resource.exists()) {
                try {
                    stream = new BufferedInputStream(new FileInputStream(resource));
                } catch (final IOException ignore) {
                    // Nothing
                }
            }
        }
        return stream;
    }
}
