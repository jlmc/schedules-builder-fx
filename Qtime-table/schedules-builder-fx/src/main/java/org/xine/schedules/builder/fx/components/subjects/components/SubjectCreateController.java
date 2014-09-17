package org.xine.schedules.builder.fx.components.subjects.components;

import org.xine.fx.guice.FXMLController;
import org.xine.schedules.builder.fx.gui.ContentController;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

@FXMLController
public class SubjectCreateController extends ContentController{
	
	public static final String NAME = "subjectCreateController";

	/** The root. */
    @FXML
    private AnchorPane root;
	
    
    public SubjectCreateController(){
    	super();
    	setName(NAME);
    }
	
	@Override
	public Node getRootNode() {
		return this.root;
	}
	
	
	

}
