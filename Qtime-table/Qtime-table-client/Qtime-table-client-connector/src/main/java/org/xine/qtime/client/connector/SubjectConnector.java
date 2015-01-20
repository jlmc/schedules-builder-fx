package org.xine.qtime.client.connector;

import java.util.List;

import org.xine.qtime.entities.Subject;

/**
 * The Interface SubjectConnector.
 */
public interface SubjectConnector  extends Connector<Subject>{

	
	/**
	 * List.
	 *
	 * @return the list
	 */
	List<Subject> list();
	
	
	/**
	 * Update.
	 *
	 * @param subject the subject
	 * @return the subject
	 */
	Subject update(final Subject subject);
	
	
	/**
	 * Creates the.
	 *
	 * @param subject the subject
	 * @return the subject
	 */
	Subject create(final Subject subject);
	
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	void delete( final Integer id);
	
	/**
	 * Gets the.
	 *
	 * @param id the id
	 * @return the subject
	 */
	Subject get(Integer id);
}
