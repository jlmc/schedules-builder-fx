package org.xine.qtime.dal.daos;

import org.xine.qtime.entities.User;

/**
 * The Interface UserDao.
 */
public interface UserDao extends GenericDAO<User, Long> {

    /**
     * Gets the by user name.
     * @param username
     *            the username
     * @return the by user name
     */
    User getByUserName(String username);

}
