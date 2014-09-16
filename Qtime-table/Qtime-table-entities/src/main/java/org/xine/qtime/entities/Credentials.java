package org.xine.qtime.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The Class Credentials.
 */
@Embeddable
public class Credentials implements Serializable {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The username. */
    @Column(name = "username", nullable = false, unique = true, length = 16)
    private String username;

    /** The passaword. */
    @Column(name = "username", nullable = false, unique = false, length = 200)
    private String passaword;

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username.
     * @param username
     *            the new username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Gets the passaword.
     * @return the passaword
     */
    public String getPassaword() {
        return this.passaword;
    }

    /**
     * Sets the passaword.
     * @param passaword
     *            the new passaword
     */
    public void setPassaword(final String passaword) {
        this.passaword = passaword;
    }

}
