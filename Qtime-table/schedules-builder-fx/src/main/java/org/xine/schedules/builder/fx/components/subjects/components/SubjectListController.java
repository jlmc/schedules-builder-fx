package org.xine.schedules.builder.fx.components.subjects.components;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.gui.ContentController;

@FXMLController
public class SubjectListController  extends ContentController{
	
	public static final String NAME = "subjectListController";

	/** The root. */
    @FXML
    private AnchorPane root;
	
    
    public SubjectListController(){
    	super();
    	setName(NAME);
    }
	
	@Override
	public Node getRootNode() {
		return this.root;
	}
	
	
	

}
