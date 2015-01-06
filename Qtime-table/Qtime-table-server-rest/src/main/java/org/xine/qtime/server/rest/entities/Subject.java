package org.xine.qtime.server.rest.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;



/**
 * The Class Subject.
 */
@XmlRootElement
public class Subject implements Serializable{
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  private Long id;

  /** The name. */
  private String name;

  /** The acronym. */
  private String acronym;

  /** The description. */
  private String description;

  /**
   * Gets the id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the acronym.
   *
   * @return the acronym
   */
  public String getAcronym() {
    return acronym;
  }

  /**
   * Sets the acronym.
   *
   * @param acronym the new acronym
   */
  public void setAcronym(String acronym) {
    this.acronym = acronym;
  }

  /**
   * Gets the description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description.
   *
   * @param description the new description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  
  
}
