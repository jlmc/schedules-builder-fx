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



//ObservableList<Subject> subjects = FXCollections
//.observableArrayList();
public class SubjectListTask extends Task<ObservableList<Subject>>{
	
	/** The Constant HTTP_LOCALHOST_SUBJECTS. */
	private static final String HTTP_LOCALHOST_SUBJECTS = "http://localhost:8080/Qtime-table-server-rest/resources";

	
	//@Override public final ReadOnlyBooleanProperty runningProperty() {return super.runningProperty(); }

	@Override
	protected ObservableList<Subject> call() throws Exception {
		return work();
	}
	
	
	private ObservableList<Subject> work(){
		final Client client = ClientBuilder.newClient();
		final List<Subject> subjects = client.target(HTTP_LOCALHOST_SUBJECTS)
				.path("subjects").request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Subject>>() {
					// NOTHING
				});
		
		ObservableList<Subject> s = FXCollections
				.observableArrayList();
		s.addAll(subjects);
		
		return s;
	
	}

}
