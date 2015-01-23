/* 
* Copyright (c) 2015 Qxine <https://github.com/jlmc>
* All Rights Reserved, unless otherwise granted permission.
*
* You may use and modify for private use, fork the official repository
* for contribution purposes, contribute code, and reuse your own code.
*/
package org.xine.qtime.client.connector;

/**
 * The Class ConnectorExeption.
 */
public class ConnectorException extends RuntimeException {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	/** The error. */
	private final int error;
	
	/**
	 * Instantiates a new connector exeption.
	 *
	 * @param error the error
	 * @param message the message
	 */
	public ConnectorException(int error, String message){
		super(message);
		this.error = error;
	}
	
	/**
	 * Instantiates a new connector exeption.
	 *
	 * @param error the error
	 */
	public ConnectorException(int error){
		super();
		this.error = error;
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public int getError() {
		return error;
	}

	

}
