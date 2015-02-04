/* 
* Copyright (c) 2015 Qxine <https://github.com/jlmc>
* All Rights Reserved, unless otherwise granted permission.
*
* You may use and modify for private use, fork the official repository
* for contribution purposes, contribute code, and reuse your own code.
*/
package org.xine.qtime.dal.exceptions;

import org.xine.qtime.dal.exceptions.types.DalExceptionType.ExceptionSubType;
import org.xine.qtime.dal.exceptions.types.DalExceptionType.ExceptionType;



/**
 * The Interface IDalException.
 */
public interface IDalException {

    /**
     * Gets the message.
     * @return the message
     */
    String getMessage();

    /**
     * Gets the cause.
     * @return the cause
     */
    Throwable getCause();

    /**
     * Gets the stack trace.
     * @return the stack trace
     */
    StackTraceElement[] getStackTrace();

    /**
     * Gets the type.
     * @return the type
     */
    ExceptionType getType();
    /**
     * Gets the sub type.
     * @return the sub type
     */
    ExceptionSubType getSubType();

    /**
     * Gets the dal exception message.
     * @return the dal exception message
     */
    String getDalExceptionMessage();
}
