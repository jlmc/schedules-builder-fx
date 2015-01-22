/* 
* Copyright (c) 2015 Qxine <https://github.com/jlmc>
* All Rights Reserved, unless otherwise granted permission.
*
* You may use and modify for private use, fork the official repository
* for contribution purposes, contribute code, and reuse your own code.
*/
package org.xine.qtime.client.connector;

/**
 * The Interface OnCompletedCallback.
 * Callback that can be implemented to receive the asynchronous processing
 *
 * @param <RESPONSE> the generic type
 */
@FunctionalInterface
public interface OnCompletedCallback<RESPONSE> {
	 
 	/**
 	 * Called when the invocation was successfully completed. Note that this does
     * not necessarily mean the response has bean fully read, which depends on the
     * parameterized invocation callback response type.
 	 *
 	 * @param response the response
 	 */
 	public void completed(RESPONSE response);
}

