/* 
 * Copyright (c) 2015 Qxine <https://github.com/jlmc>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */
package org.xine.qtime.fxdesktop.tasks;

import javafx.concurrent.Task;

import org.xine.qtime.client.connector.SubjectConnector;
import org.xine.qtime.entities.Subject;

import com.google.inject.Inject;

/**
 * The Class TaskProveiderImpl.
 */
public class TaskProviderImpl implements TaskProvider {

	/** The connector. */
	@Inject
	private SubjectConnector connector;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.xine.qtime.fxdesktop.tasks.TaskProveider#getLoadSubjectsListTask()
	 */
	public LoadSubjectsTask getLoadSubjectsListTask() {
		return new LoadSubjectsTask(connector);
	}

	/**
	 * Gets the gets the subject task.
	 *
	 * @param id
	 *            the id
	 * @return the gets the subject task
	 */
	public Task<Subject> getGetSubjectTask(Integer id) {
		return new GetSubjectTask(connector, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.xine.qtime.fxdesktop.tasks.TaskProvider#getSaveSubjectTask(org.xine
	 * .qtime.entities.Subject)
	 */
	@Override
	public Task<Subject> getSaveSubjectTask(Subject subject) {
		return new SaveSubjectTask(this.connector, subject);
	}
}
