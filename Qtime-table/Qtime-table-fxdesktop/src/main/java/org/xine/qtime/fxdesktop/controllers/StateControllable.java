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
package org.xine.qtime.fxdesktop.controllers;

import java.util.Collection;

/**
 * The Interface StateControllable.
 *
 * @param <T> the generic type
 */
public interface StateControllable<T> {

	/**
	 * On entity removed.
	 *
	 * @param objs the objs
	 */
	default void onEntityRemoved(final Collection<T> objs){
		//do nothing by default
	}
	
	/**
	 * On entity added.
	 *
	 * @param objs the objs
	 */
	default void onEntityAdded(final Collection<T> objs) {
		//do nothing by default
	}
   
    /**
     * On entity edited.
     *
     * @param objs the objs
     */
    default void onEntityEdited(final T objs) {
    	//do nothing by default
    }
}
