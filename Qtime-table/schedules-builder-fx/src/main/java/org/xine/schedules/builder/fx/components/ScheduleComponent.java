package org.xine.schedules.builder.fx.components;

import org.xine.schedules.builder.fx.gui.ApplicationController;

/**
 * The Interface ScheduleComponent.
 */
public interface ScheduleComponent {
	
	 /**
 	 *  The application controller.
 	 *
 	 * @param c the new application controller
 	 */
    public void setApplicationController(ApplicationController c);
    
    
    /**
     * On activate.
     * Callback - design for extension
     */
    public void onActivate();
    
    /**
     * On deactivate.
     * Callback - design for extension
     */
    public void onDeactivate();

    /**
     * On quit.
     * Callback - design for extension
     */
    public void onQuit();

}
