/* 
 * Copyright (c) 2015 Qxine <https://github.com/jlmc>
 * All Rights Reserved, unless otherwise granted permission.
 *
 * You may use and modify for private use, fork the official repository
 * for contribution purposes, contribute code, and reuse your own code.
 */
package org.xine.qtime.fxdesktop.tasks;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import org.xine.qtime.client.connector.ConnectorException;
import org.xine.qtime.client.connector.SubjectConnector;
import org.xine.qtime.entities.Subject;

/**
 * The Class LoadSubjectsTask.
 */
public class LoadSubjectsTask extends Task<ObservableList<Subject>> {

	/** The connector. */
	private SubjectConnector connector;

	/**
	 * Instantiates a new load subjects task.
	 *
	 * @param connector the connector
	 */
	public LoadSubjectsTask(SubjectConnector connector) {
		super();
		this.connector = connector;
	}

	/**
	 * Invoked when the Task is executed, the call method must be overridden and
	 * implemented by subclasses. The call method actually performs the
	 * background thread logic. Only the updateProgress, updateMessage, and
	 * updateTitle methods of Task may be called from code within this method.
	 * Any other interaction with the Task from the background thread will
	 * result in runtime exceptions.
	 * 
	 * Overrides: call() in Task Returns: The result of the background work, if
	 * any. Throws: Exception - an unhandled exception which occurred during the
	 * background operation
	 *
	 * @return the observable list
	 * @throws Exception the exception
	 */
	@Override
	protected ObservableList<Subject> call() throws Exception {
		return FXCollections.observableArrayList(job());
	}

	/**
	 * Job.
	 *
	 * @return the list
	 */
	private List<Subject> job() {
		try {
			return connector.list();
		} catch (ConnectorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Subject>();
	}

}
