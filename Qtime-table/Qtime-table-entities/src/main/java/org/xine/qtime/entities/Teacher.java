package org.xine.qtime.entities;

import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class Teacher.
 */
public class Teacher extends Person{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The preferencies. */
	private Set<Preference> preferencies;
	
	/**
	 * Gets the preferencies.
	 *
	 * @return the preferencies
	 */
	public Set<Preference> getPreferencies() {
		return preferencies;
	}


	/**
	 * Sets the preferencies.
	 *
	 * @param preferencies the new preferencies
	 */
	public void setPreferencies(Set<Preference> preferencies) {
		this.preferencies = preferencies;
	}


	

}
