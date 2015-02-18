/* 
* Copyright (c) 2015 Qxine <https://github.com/jlmc>
* All Rights Reserved, unless otherwise granted permission.
*
* You may use and modify for private use, fork the official repository
* for contribution purposes, contribute code, and reuse your own code.
*/
package org.xine.qtime.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The Class Subject.
 */
@Entity
public class Subject implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The name. */
	@Column(unique= true, nullable=false, length=150)
	private String name;

	/** The acronym. */
	@Column(unique= true, nullable=false, length=10)
	private String acronym;

	/** The description. */
	@Column(length=250)
	private String description;

	/**
	 * Instantiates a new subject.
	 */
	public Subject() {
		super();
	}

	/**
	 * Instantiates a new subject.
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param acronym
	 *            the acronym
	 * @param description
	 *            the description
	 */
	public Subject(final Long id, final String name, final String acronym, final String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.acronym = acronym;
	}

	/**
	 * Instantiates a new subject.
	 * @param name
	 *            the name
	 * @param acronym
	 *            the acronym
	 * @param description
	 *            the description
	 */
	public Subject(final String name, final String acronym, final String description) {
		this.name = name;
		this.description = description;
		this.acronym = acronym;
	}

	/**
	 * Gets the id.
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 * @param id
	 *            the new id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 * @param name
	 *            the new name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 * @param description
	 *            the new description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Gets the acronym.
	 * @return the acronym
	 */
	public String getAcronym() {
		return this.acronym;
	}

	/**
	 * Sets the acronym.
	 * @param acronym
	 *            the new acronym
	 */
	public void setAcronym(final String acronym) {
		this.acronym = acronym;
	}

}
