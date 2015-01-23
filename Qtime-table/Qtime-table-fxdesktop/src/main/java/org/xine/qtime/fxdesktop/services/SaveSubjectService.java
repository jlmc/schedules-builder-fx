/**
 * 
 */
package org.xine.qtime.fxdesktop.services;

import org.xine.qtime.entities.Subject;
import org.xine.qtime.fxdesktop.tasks.TaskProvider;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * @author jcosta
 *
 */
public class SaveSubjectService extends Service<Subject> {
	
	/** The task provider. */
	private TaskProvider taskProvider;
	
	/** The id. */
	private Subject subject;

	@Override
	protected Task<Subject> createTask() {
		return taskProvider.getSaveSubjectTask(this.subject);
		
	}
	
}
