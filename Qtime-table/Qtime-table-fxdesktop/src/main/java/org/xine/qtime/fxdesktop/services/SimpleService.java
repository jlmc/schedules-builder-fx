/* 
* Copyright (c) 2015 Qxine <https://github.com/jlmc>
* All Rights Reserved, unless otherwise granted permission.
*
* You may use and modify for private use, fork the official repository
* for contribution purposes, contribute code, and reuse your own code.
*/
package org.xine.qtime.fxdesktop.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.util.Callback;



/**
 * The Class SimpleService.
 *
 * @param <R> the generic type
 * @param <P> the generic type
 */
public class SimpleService<R,P> extends Service<R>{

	/** The callback. */
	private Callback<P, R> callback;

	/** The param. */
	private P param;

	/**
	 * Perform.
	 *
	 * @param callback the callback
	 * @param param the param
	 */
	public void perform(Callback<P, R> callback, P param){
		super.reset();
		this.callback = callback;
		this.param = param;
		this.start();
	}

	/* (non-Javadoc)
	 * @see javafx.concurrent.Service#createTask()
	 */
	@Override
	protected Task<R> createTask() {
		return new Task<R>(){
			@Override
			protected R call() throws Exception {
				return callback.call(param);
			}
		};
	}
}
