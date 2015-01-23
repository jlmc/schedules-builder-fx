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

/**
 * The Class SaveSubjectTask.
 */
public class SaveSubjectTask extends Task<Subject> {

	/** The connector. */
	private SubjectConnector connector;
	
	/** The subject. */
	private Subject subject;
	
	/**
	 * Instantiates a new save subject task.
	 *
	 * @param connector the connector
	 * @param subject the subject
	 */
	public SaveSubjectTask(SubjectConnector connector, Subject subject) {
		this.connector = connector;
		this.subject = subject;
	}

	/* (non-Javadoc)
	 * @see javafx.concurrent.Task#call()
	 */
	@Override
	protected Subject call() throws Exception {
		return this.connector.create(this.subject);
	}

}
