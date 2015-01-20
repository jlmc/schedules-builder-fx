package org.xine.qtime.fxdesktop.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.xine.qtime.fxdesktop.utils.DirectoryUtils;

import com.google.inject.Inject;

/**
 * The Class SimplePropertyManager.
 */
public class SimplePropertyManager implements PropertyManager {

	/** The properties file. */
	private File propertiesFile;
	
	/** The properties. */
	private Properties properties;
	
	/** The lock. */
	private Lock lock = new ReentrantLock();
	
	
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
		try{
			propertiesFile = new File(DirectoryUtils.getWorkingDirectory(), "launcher.properties");
			if(!propertiesFile.exists()){
				propertiesFile.getParentFile().mkdirs();
				propertiesFile.createNewFile();
			}
			
			properties = new Properties();
			properties.load(new FileInputStream(propertiesFile));
		}catch(Exception e){
			throw new IllegalStateException("Could not open properties file " + propertiesFile.getAbsolutePath());
		}
		
	}



	/* (non-Javadoc)
	 * @see org.xine.qtime.fxdesktop.services.PropertyManager#saveProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveProperty(String key, String value) {
		lock.lock();
		try {
			properties.put(key, value);
			properties.store(new FileOutputStream(propertiesFile), "");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	/* (non-Javadoc)
	 * @see org.xine.qtime.fxdesktop.services.PropertyManager#getPropertity(java.lang.String)
	 */
	@Override
	public String getPropertity(String key) {
		return properties.getProperty(key);
	}

}
