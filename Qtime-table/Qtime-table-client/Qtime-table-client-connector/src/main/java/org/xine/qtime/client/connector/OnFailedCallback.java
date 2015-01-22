/* 
 * Copyright (c) 2015 Qxine <https://github.com/jlmc>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */
package org.xine.qtime.client.connector;

/**
 * The Interface OnFailedCallback.
 */

public interface OnFailedCallback {

	/**
	 * Called when the invocation has failed for any reason.
	 *
	 * @param throwable
	 *            the throwable
	 */
	default void failed(Throwable throwable) {
	}

}
