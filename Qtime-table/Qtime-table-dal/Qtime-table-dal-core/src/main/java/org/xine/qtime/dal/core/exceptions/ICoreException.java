package org.xine.qtime.dal.core.exceptions;

import org.xine.qtime.dal.core.exceptions.types.CoreExceptionType;

/**
 * The Interface ICoreException.
 */
public interface ICoreException {

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
    CoreExceptionType.ExceptionType getType();

    /**
     * Gets the sub type.
     * @return the sub type
     */
    CoreExceptionType.ExceptionSubType getSubType();

    /**
     * Gets the dal exception message.
     * @return the dal exception message
     */
    String getCoreExceptionMessage();
}
