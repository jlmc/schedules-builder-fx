package org.xine.qtime.client.fx.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.xine.qtime.client.fx.utils.DirectoryUtils;

import com.google.inject.Inject;

public class SimplePropertyManager implements PropertyManager {

    private File propertiesFile;
    private Properties properties;
    private final Lock lock = new ReentrantLock();

    @Inject
    public SimplePropertyManager() {
        init();
    }

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

    @Override
    public String getPropertity(final String key) {
        return this.properties.getProperty(key);
    }

}
