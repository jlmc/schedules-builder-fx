package org.xine.qtime.entities;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Person.
 */
public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The cc. */
	private long cc;
	
	/** The firts name. */
	private String firtsName;
	
	/** The last name. */
	private String lastName;

	/**
	 * Gets the cc.
	 *
	 * @return the cc
	 */
	public long getCc() {
		return cc;
	}

	/**
	 * Sets the cc.
	 *
	 * @param cc the new cc
	 */
	public void setCc(long cc) {
		this.cc = cc;
	}

	/**
	 * Gets the firts name.
	 *
	 * @return the firts name
	 */
	public String getFirtsName() {
		return firtsName;
	}

	/**
	 * Sets the firts name.
	 *
	 * @param firtsName the new firts name
	 */
	public void setFirtsName(String firtsName) {
		this.firtsName = firtsName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
