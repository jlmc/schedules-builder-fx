/*
 * Copyright (c) 2012 Spout LLC <http://www.spout.org>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */
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
