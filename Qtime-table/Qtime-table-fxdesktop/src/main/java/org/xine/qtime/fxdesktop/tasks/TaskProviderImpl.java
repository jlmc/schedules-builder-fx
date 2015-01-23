/* 
* Copyright (c) 2015 Qxine <https://github.com/jlmc>
* All Rights Reserved, unless otherwise granted permission.
*
* You may use and modify for private use, fork the official repository
* for contribution purposes, contribute code, and reuse your own code.
*/
package org.xine.qtime.fxdesktop.tasks;

import org.xine.qtime.client.connector.SubjectConnector;

import com.google.inject.Inject;

/**
 * The Class TaskProveiderImpl.
 */
public class TaskProviderImpl implements TaskProvider{
	
	@Inject
	private SubjectConnector connector;
	
	/* (non-Javadoc)
	 * @see org.xine.qtime.fxdesktop.tasks.TaskProveider#getLoadSubjectsListTask()
	 */
	public LoadSubjectsTask getLoadSubjectsListTask(){
		return new LoadSubjectsTask(connector);
	}
}
