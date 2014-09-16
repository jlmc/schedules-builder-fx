package org.xine.qtime.entities;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class User.
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** The credentials. */
	@Embedded
	private Credentials credentials;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Gets the credentials.
	 *
	 * @return the credentials
	 */
	public Credentials getCredentials() {
		return this.credentials;
	}

	/**
	 * Sets the credentials.
	 *
	 * @param credentials
	 *            the new credentials
	 */
	public void setCredentials(final Credentials credentials) {
		this.credentials = credentials;
	}

}
