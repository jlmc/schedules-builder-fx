/* 
* Copyright (c) 2015 Qxine <https://github.com/jlmc>
* All Rights Reserved, unless otherwise granted permission.
*
* You may use and modify for private use, fork the official repository
* for contribution purposes, contribute code, and reuse your own code.
*/
package org.xine.qtime.fxdesktop.services;

import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import org.xine.qtime.entities.Subject;
import org.xine.qtime.fxdesktop.tasks.TaskProvider;

/**
 * The Class LoadSubjects.
 */
public class LoadSubjects extends Service<ObservableList<Subject>> {
	

	/** The task provider. */
	TaskProvider taskProvider;
	
	
	/**
	 * Instantiates a new load subjects.
	 *
	 * @param taskProvider the task provider
	 */
	public LoadSubjects(TaskProvider taskProvider) {
		this.taskProvider = taskProvider;
	}


	/* (non-Javadoc)
	 * @see javafx.concurrent.Service#createTask()
	 */
	@Override
	protected Task<ObservableList<Subject>> createTask() {
		return this.taskProvider.getLoadSubjectsListTask();
	}

}