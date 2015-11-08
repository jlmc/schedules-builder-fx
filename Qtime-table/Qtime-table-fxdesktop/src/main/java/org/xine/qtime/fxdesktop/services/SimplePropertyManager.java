/*
 * Copyright (c) 2012 Spout LLC <http://www.spout.org>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */
package org.xine.qtime.fxdesktop.services;

import org.xine.qtime.fxdesktop.utils.DirectoryUtils;

import com.google.inject.Inject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class SimplePropertyManager.
 */
public class SimplePropertyManager implements PropertyManager {

    /** The properties file. */
    private File propertiesFile;

    /** The properties. */
    private Properties properties;

    /** The lock. */
    private final Lock lock = new ReentrantLock();

    /**
     * Constructor. Should never be called directly.
     */
    @Inject
    public SimplePropertyManager() {
        init();
    }

    /**
     * Inits the.
     */
    private void init() {
        try {
            this.propertiesFile = new File(DirectoryUtils.getWorkingDirectory(),
                    "launcher.properties");
            if (!this.propertiesFile.exists()) {
                this.propertiesFile.getParentFile().mkdirs();
                this.propertiesFile.createNewFile();
            }

            this.properties = new Properties();
            this.properties.load(new FileInputStream(this.propertiesFile));
        } catch (final Exception e) {
            throw new IllegalStateException("Could not open properties file "
                    + this.propertiesFile.getAbsolutePath());
        }

    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.services.PropertyManager#saveProperty(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void saveProperty(final String key, final String value) {
        this.lock.lock();
        try {
            this.properties.put(key, value);
            this.properties.store(new FileOutputStream(this.propertiesFile), "");
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }

    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.fxdesktop.services.PropertyManager#getPropertity(java.lang.String)
     */
    @Override
    public String getPropertity(final String key) {
        return this.properties.getProperty(key);
    }

}
