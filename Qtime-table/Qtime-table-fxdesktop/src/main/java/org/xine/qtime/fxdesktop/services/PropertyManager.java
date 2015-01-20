package org.xine.qtime.fxdesktop.services;

/**
 * The Interface PropertyManager.
 */
public interface PropertyManager {
	
	/**
	 * Stores a property.
	 *
	 * @param key the key
	 * @param value the value
	 */
	void saveProperty(String key, String value);
	
	/**
	 * Retrieves a property value.
	 *
	 * @param key the key
	 * @return the propertity
	 */
	String getPropertity(String key);

}
