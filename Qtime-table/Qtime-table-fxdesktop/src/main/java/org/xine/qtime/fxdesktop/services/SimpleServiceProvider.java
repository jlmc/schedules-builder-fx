package org.xine.qtime.fxdesktop.services;

import javax.inject.Inject;

import org.xine.qtime.fxdesktop.tasks.TaskProvider;

public class SimpleServiceProvider implements ServiceProvider{
	
	
	private TaskProvider taskProvider;


	@Inject
	SimpleServiceProvider(TaskProvider taskProvider){
		super();
		this.taskProvider = taskProvider;
	}


	@Override
	public LoadSubjects getLoadSubjects() {
		return new LoadSubjects(taskProvider);
	}
	
	

}
