package org.xine.qtime.dal.daos;

import org.hibernate.Transaction;

/**
 * The Interface Transactable.
 */
public interface Transactable {

    /**
     * Begin transaction.
     * @return the session
     */
    Transaction beginTransaction();

    /**
     * Commit transaction.
     */
    void commitTransaction();

    /**
     * Rollback transaction.
     */
    void rollbackTransaction();

}
