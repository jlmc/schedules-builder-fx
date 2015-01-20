package org.xine.qtime.client.connector;

import java.util.List;


/**
 * The Interface Connector.
 *
 * @param <T> the generic type
 */
public interface Connector<T> {
	
	/**
	 * List.
	 *
	 * @return the list
	 */
	List<T> list();

	/**
	 * Update.
	 *
	 * @param subject the subject
	 * @return the t
	 */
	T update(final T subject);

	/**
	 * Creates the.
	 *
	 * @param subject the subject
	 * @return the t
	 */
	T create(final T subject);

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
	 * @return the t
	 */
	T get(Integer id);
}
