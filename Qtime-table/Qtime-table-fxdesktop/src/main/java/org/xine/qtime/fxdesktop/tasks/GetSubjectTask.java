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
 * The Class GetSubjectTask.
 */
public class GetSubjectTask extends Task<Subject> {

	/** The connector. */
	private final SubjectConnector connector;
	
	/** The id. */
	private Integer id;
	
	/**
	 * Instantiates a new gets the subject task.
	 *
	 * @param connector the connector
	 * @param id the id
	 */
	public GetSubjectTask(SubjectConnector connector, Integer id){
		super();
		this.connector = connector;
	}
	
	/* (non-Javadoc)
	 * @see javafx.concurrent.Task#call()
	 */
	@Override
	protected Subject call() throws Exception {
		return connector.get(this.id);
	}

}
