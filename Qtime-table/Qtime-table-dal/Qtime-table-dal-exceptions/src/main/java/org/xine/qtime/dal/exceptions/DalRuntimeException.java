/* 
* Copyright (c) 2015 Qxine <https://github.com/jlmc>
* All Rights Reserved, unless otherwise granted permission.
*
* You may use and modify for private use, fork the official repository
* for contribution purposes, contribute code, and reuse your own code.
*/
package org.xine.qtime.dal.exceptions;

import java.util.Arrays;

import org.slf4j.LoggerFactory;
import org.xine.qtime.dal.exceptions.enumTypes.DalExceptionType;
import org.xine.qtime.dal.exceptions.enumTypes.DalExceptionUtil;


/**
 * The Class DalRuntimeException.
 */
class DalRuntimeException extends RuntimeException implements IDalException {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -2567929786121392716L;

    /**
     * The type.
     */
    private final DalExceptionType.ExceptionType type;
    /**
     * The sub type.
     */
    private DalExceptionType.ExceptionSubType subType = null;

    /**
     * The dal exception message.
     */
    private final String dalExceptionMessage;

    /**
     * The Constructor.
     *
     * @param message the message
     * @param cause the cause
     * @param type the type
     * @param subType the sub type
     */
    @SuppressWarnings("rawtypes")
    DalRuntimeException(final String message, final Throwable cause, final DalExceptionType.ExceptionType type, final DalExceptionType.ExceptionSubType subType) {
        super(DalExceptionUtil.generateMessage(message, cause, type, subType), cause);
        final StackTraceElement[] stackTrace = Arrays.copyOfRange(getStackTrace(), 1, getStackTrace().length);
        setStackTrace(stackTrace);
        this.dalExceptionMessage = message == null ? DalExceptionUtil.getMessage(cause) : message;
        this.type = type;
        this.subType = subType;

        Class clazz;
        try {
            clazz = Class.forName(stackTrace[stackTrace.length - 1].getClassName());
        } catch (final ClassNotFoundException ex) {
            clazz = this.getClass();
        }

        LoggerFactory.getLogger(clazz).error(getMessage(), this);
    }

    /**
     * The Constructor.
     *
     * @param message the message
     * @param de the DalException
     * @param type the type
     * @param subType the sub type
     */
    DalRuntimeException(final String message, final IDalException de, final DalExceptionType.ExceptionType type, final DalExceptionType.ExceptionSubType subType) {
        super(DalExceptionUtil.generateMessage(message, de, type, subType));

        this.dalExceptionMessage = message == null ? de.getMessage() : message;
        this.type = type == null ? de.getType() : type;
        this.subType = subType == null ? de.getSubType() : subType;
        setStackTrace(de.getStackTrace());
    }


  /**
     * Gets the type.
     *
     * @return the type
     */
    @Override
    public DalExceptionType.ExceptionType getType() {
        return this.type;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    @Override
    public DalExceptionType.ExceptionSubType getSubType() {
        return this.subType;
    }

    /**
     * Gets the dal exception message.
     *
     * @return the dal exception message
     */
    @Override
    public String getDalExceptionMessage() {
        return this.dalExceptionMessage;
    }
}
