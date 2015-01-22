/* 
* Copyright (c) 2015 Qxine <https://github.com/jlmc>
* All Rights Reserved, unless otherwise granted permission.
*
* You may use and modify for private use, fork the official repository
* for contribution purposes, contribute code, and reuse your own code.
*/
package org.xine.qtime.client.connector.manager;


import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.xine.qtime.entities.Subject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;




/**
 * The Class SubjectListTask.
 */
public class SubjectListTask extends Task<ObservableList<Subject>>{

	/** The Constant HTTP_LOCALHOST_SUBJECTS. */
	private static final String HTTP_LOCALHOST_SUBJECTS = "http://localhost:8080/Qtime-table-server-rest/resources";


	//@Override public final ReadOnlyBooleanProperty runningProperty() {return super.runningProperty(); }

	/* (non-Javadoc)
	 * @see javafx.concurrent.Task#call()
	 */
	@Override
	protected ObservableList<Subject> call() throws Exception {

		return FXCollections
				.observableArrayList(work());


	}


	/**
	 * Work.
	 *
	 * @return the list
	 */
	private List<Subject> work(){
		final Client client = ClientBuilder.newClient();
		final List<Subject> subjects = client.target(HTTP_LOCALHOST_SUBJECTS)
				.path("subjects").request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Subject>>() {
					// NOTHING
				});



		return subjects;

	}

}
