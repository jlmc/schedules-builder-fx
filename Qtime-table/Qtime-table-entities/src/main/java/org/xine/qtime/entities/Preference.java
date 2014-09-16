package org.xine.qtime.entities;

import java.io.Serializable;

/**
 * The Class Preference.
 */
public class Preference implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The type. */
	private PreferenceType type;

	public PreferenceType getType() {
		return type;
	}

	public void setType(PreferenceType type) {
		this.type = type;
	}

}
