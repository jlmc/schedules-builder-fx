package org.xine.schedules.builder.fx.components.subjects;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import org.xine.fx.guice.FXMLComponent;
import org.xine.fx.guice.GuiceFXMLLoader;
import org.xine.schedules.builder.fx.components.ScheduleComponent;
import org.xine.schedules.builder.fx.gui.ApplicationController;


@FXMLComponent(resources="org.xine.schedules.builder.fx.components.subjects.SubjectsPane")
public class SubjectsPane extends AnchorPane implements ScheduleComponent{
	
	@FXML
	private AnchorPane root;
	
	@FXML
	private Label text;
	
	
	
	
	
	/** The fxml loader. */
	@SuppressWarnings("unused")
	@Inject
	private GuiceFXMLLoader fxmlLoader;
	
	
	
	
	
	public ApplicationController applicationController;
	
	
	public SubjectsPane(){
		super();
		System.out.println("KKKK- test");
	}
	
	@FXML
	public void initialize() {
		System.out.println("init - test");
		//define list
		//define create
		//define active view
		
	}

	@Override
	public void setApplicationController(
			ApplicationController c) {
		this.applicationController = c;
	}

	@Override
	public void onActivate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeactivate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onQuit() {
		// TODO Auto-generated method stub
		
	}

}
