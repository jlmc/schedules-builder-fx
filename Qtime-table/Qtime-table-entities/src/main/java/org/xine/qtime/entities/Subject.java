package org.xine.qtime.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique= true, nullable=false, length=150)
	private String name;

	@Column(unique= true, nullable=false, length=10)
	private String acronym;

	@Column(length=250)
	private String description;

	public Subject() {
		super();
	}

	public Subject(final Long id, final String name, final String acronym, final String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.acronym = acronym;
	}

	public Subject(final String name, final String acronym, final String description) {
		this.name = name;
		this.description = description;
		this.acronym = acronym;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getAcronym() {
		return this.acronym;
	}

	public void setAcronym(final String acronym) {
		this.acronym = acronym;
	}

}
