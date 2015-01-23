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

import org.xine.qtime.entities.Subject;
import org.xine.qtime.fxdesktop.tasks.TaskProvider;

/**
 * The Class GetSubjectService.
 */
public class GetSubjectService extends Service<Subject>{

	/** The task provider. */
	private TaskProvider taskProvider;
	
	/** The id. */
	private Integer id;
	
	/* (non-Javadoc)
	 * @see javafx.concurrent.Service#createTask()
	 */
	@Override
	protected Task<Subject> createTask() {
		return taskProvider.getGetSubjectTask(this.id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
